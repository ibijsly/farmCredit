package com.farmCredit.farmCredit.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.experiment.InstanceQuery;

import java.io.*;

@Component
public class ClassifierAlgorithm {
    String driver = "com.mysql.jdbc.Driver";
    @Value("${app.datasource.jdbc-url}") String url;
    @Value("${app.datasource.username}") String username;
    @Value("${app.datasource.password}") String password;

    public static J48 tree = null;

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

    public void train(){
        String[] options = new String[2];
        options[0] = "-U";
        options[1] = "1";

        tree = new J48();
        try {
            tree.setOptions(options);     // set the options
            Instances data = readFromDatabaseTable("select cooperative_membership, credit_ratio, farm_productivity, payment_history, performance_history, status from dataset");

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
//            System.out.println("1-NN accuracy on training data:\n" + eval.pctCorrect()/100);
//            System.out.println("1-NN accuracy on separate test data:\n" + eval.pctCorrect()/100);

            System.out.println(eval.toSummaryString("\nResults\n======\n", true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void classify(long id){
        try{
            Instances data = readFromDatabaseTable("select cooperative_membership, credit_ratio, farm_productivity, payment_history, performance_history, status from dataset where id = " + id);

            System.out.println(tree.classifyInstance(data.instance(0)));
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

    public DriverManagerDataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }
}
