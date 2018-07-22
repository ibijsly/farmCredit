package com.farmCredit.farmCredit.utilities;

import com.farmCredit.farmCredit.model.Dataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.experiment.InstanceQuery;

import java.io.*;
import java.util.ArrayList;

@Component
public class ClassifierAlgorithm {
    String driver = "com.mysql.jdbc.Driver";
    @Value("${app.datasource.jdbc-url}") String url;
    @Value("${app.datasource.username}") String username;
    @Value("${app.datasource.password}") String password;

    public static J48 tree = null;
    public static  Instances data = null;
    public static Logger logger = LoggerFactory.getLogger(ClassifierAlgorithm.class);

    /*Train data from a CSV file*/
    public void train(String filePath, String fileName){
        String[] options = new String[1];
        options[0] = "-U";

        J48 tree = new J48();
        try {
            tree.setOptions(options);     // set the options

            tree.buildClassifier(readCSVDataset(filePath, fileName));   // build classifier
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Train data from a Database Table*/
    public void train(){
        String[] options = new String[2];
        options[0] = "-U";
        options[1] = "1";

        tree = new J48();
        try {
            tree.setOptions(options);     // set the options
            data = readFromDatabaseTable("select cooperative_membership, credit_ratio, farm_productivity, payment_history, performance_history, status from dataset");

            /*Randomise data before the train test split*/
            data.randomize(new java.util.Random());
            Instances trainData = data.trainCV(2, 0);
            Instances testData = data.testCV(2, 0);

            trainData.setClassIndex(data.numAttributes() - 1);
            testData.setClassIndex(data.numAttributes() - 1);

            tree.buildClassifier(trainData);   // build classifier
            System.out.println(tree.classifyInstance(testData.instance(0)));
            Evaluation eval = new Evaluation(trainData);
            eval.evaluateModel(tree, trainData);
            eval.evaluateModel(tree, testData);

            logger.info("1-NN accuracy on training data: " + eval.pctCorrect()/100);
            logger.info("1-NN accuracy on separate test data: " + eval.pctCorrect()/100);

//            System.out.println(eval.toSummaryString("\nResults\n======\n", true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void classify(Dataset dataset){
        try{

//            ArrayList<Attribute> atts = new ArrayList<Attribute>();
//            ArrayList<String> classVal = new ArrayList<String>();
            FastVector atts = new FastVector();
            ArrayList<String> classVal = new ArrayList<String>();
            classVal.add("Credit Worthy");
            classVal.add("Not Credit Worthy");

            atts.addElement(new Attribute("cooperative_membership"));
            atts.addElement(new Attribute("credit_ratio"));
            atts.addElement(new Attribute("farm_productivity"));
            atts.addElement(new Attribute("payment_history"));
            atts.addElement(new Attribute("performance_history"));
            atts.addElement(new Attribute("status"));

            Instances dataRaw = new Instances("TestInstances", atts, 0);

            double[] attribute = {dataset.getCooperativeMembership(), dataset.getCreditRatio(), dataset.getFarmProductivity(), dataset.getPaymentHistory(), dataset.getPerformanceHistory()};
            Instance instance = new Instance(1.0, attribute);

            dataRaw.add(instance);
            dataRaw.setClassIndex(dataRaw.numAttributes() -1);
            System.out.println(instance.toString());
            double prediction = tree.classifyInstance(dataRaw.firstInstance());
            System.out.println(prediction);
            System.out.println(data.numInstances());
//            String predictionValue = data.classAttribute().value((int) prediction);
            System.out.println("");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public Instances readCSVDataset(String filePath, String fileName){
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(convertCSVtoArff(filePath, fileName)));
            Instances data = new Instances(reader);
            reader.close();
            // setting class attribute
            data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String convertCSVtoArff(String csvPath, String fileName) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(csvPath + fileName));
        Instances data = loader.getDataSet();

        // save ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File(csvPath + "converted.arff"));
        saver.setDestination(new File(csvPath + "converted.arff"));
        saver.writeBatch();

        return csvPath + "converted.arff";
    }

    public Instances readFromDatabaseTable(String queryString) throws Exception {
        InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL(url);
        query.setUsername(username);
        query.setPassword(password);
        query.setQuery(queryString);
        // You can declare that your data set is sparse
        // query.setSparseData(true);
        Instances data = query.retrieveInstances();
        data.setClassIndex(data.numAttributes() - 1);

        return data;
    }
}
