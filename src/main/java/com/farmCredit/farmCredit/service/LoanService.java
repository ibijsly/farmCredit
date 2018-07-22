package com.farmCredit.farmCredit.service;

import com.farmCredit.farmCredit.model.Dataset;
import com.farmCredit.farmCredit.model.Farmer;
import com.farmCredit.farmCredit.repository.DatasetRepository;
import com.farmCredit.farmCredit.repository.LoanRepository;
import com.farmCredit.farmCredit.utilities.ClassifierAlgorithm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.farmCredit.farmCredit.utilities.PerformanceMetrics.*;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final DatasetRepository datasetRepository;
    private final ClassifierAlgorithm classifierAlgorithm;

    @Autowired
    public LoanService(LoanRepository loanRepository, DatasetRepository datasetRepository, ClassifierAlgorithm classifierAlgorithm) {
        this.loanRepository = loanRepository;
        this.datasetRepository = datasetRepository;
        this.classifierAlgorithm = classifierAlgorithm;
    }

    public Dataset getPerformance(Farmer farmer, String mode){

        Long totalLoanVolume = loanRepository.getTotalCount(farmer.getId());
        totalLoanVolume = (totalLoanVolume != null) ? totalLoanVolume : 0L;
        Long totalReturnedCashVolume = loanRepository.getCashCount(farmer.getId());
        totalReturnedCashVolume = (totalReturnedCashVolume != null) ? totalReturnedCashVolume : 0L;
        Long totalReturnedProduceVolume = loanRepository.getProduceCount(farmer.getId());
        totalReturnedProduceVolume = (totalReturnedProduceVolume != null) ? totalReturnedProduceVolume : 0L;
        Long debtVolume = loanRepository.getDebtCount(farmer.getId());
        debtVolume = (debtVolume != null) ? debtVolume : 0L;

        Double totalLoanValue = loanRepository.getTotalValue(farmer.getId());
        totalLoanValue = (totalLoanValue != null) ? totalLoanValue : 0D;
        Double totalReturnedCashValue = loanRepository.getCashValue(farmer.getId());
        totalReturnedCashValue = (totalReturnedCashValue != null) ? totalReturnedCashValue : 0D;
        Double totalReturnedProduceValue = loanRepository.getProduceValue(farmer.getId());
        totalReturnedProduceValue = (totalReturnedProduceValue != null) ? totalReturnedProduceValue : 0D;
        Double debt = loanRepository.getDebt(farmer.getId());
        debt = (debt != null) ? debt : 0D;

        double farmProductivity = farmProductivity(farmer.getFarmSize(), farmer.getAverageOutput());
        double paymentHistoryVolume = paymentHistoryVolume(totalLoanVolume, totalReturnedCashVolume, totalReturnedProduceVolume);
        double paymentHistoryValue = paymentHistoryValue(totalLoanValue, totalReturnedCashValue, totalReturnedProduceValue);
        double cooperativeMembership = farmer.getCooperativeId() == null ? 0 : 1;
        double creditRatioValue = creditRatioValue(totalLoanValue, debt);
        double creditRatioVolume = creditRatioVolume(totalLoanVolume, debtVolume);


//        double performanceHistory =  (2.00d + Math.random() * 8.00d) * (35.00d / 100.00d);     /*This has a 35% effect on the general metrics*/;
        double performanceHistory =  Math.random() * (35.00d / 100.00d);     /*This has a 35% effect on the general metrics*/;
        double paymentHistory = ((2.00d * paymentHistoryValue + paymentHistoryVolume) / 3.00d) * (35.00d / 100.00d);     /*This has a 35% effect on the general metrics*/
        farmProductivity = farmProductivity * (10.00d / 100.00d);               /*This takes 10% effect*/
        double creditRatio = ((2 * creditRatioValue + creditRatioVolume) / 3.00d) * (15.00d / 100.00d);     /*This has a 15% effect on the general metrics*/
        cooperativeMembership = cooperativeMembership * (05.00d / 100.00d);     /*This takes 5% effect*/

        double performanceKey = performanceHistory + paymentHistory + farmProductivity + creditRatio + cooperativeMembership;
        LoggerFactory.getLogger(LoanService.class).info("performanceHistory: " + performanceHistory + ", farmProductivity: " + farmProductivity + ", paymentHistory: " + paymentHistory + ", cooperativeMembership: " + cooperativeMembership + ", creditRatio: " + creditRatio + ", farmerId: " + farmer.getId() );
        LoggerFactory.getLogger(LoanService.class).info("farmerId: " + farmer.getId() + ", performanceKey: " + performanceKey);

        datasetRepository.save(new Dataset(performanceHistory, paymentHistory, farmProductivity, creditRatio, cooperativeMembership, performanceKey > 0.62 ? "Credit Worthy" : "Not Credit Worthy"));
        if (mode.equalsIgnoreCase("dataset"))
            return datasetRepository.save(new Dataset(performanceHistory, paymentHistory, farmProductivity, creditRatio, cooperativeMembership, performanceKey > 0.62 ? "Credit Worthy" : "Not Credit Worthy"));
        else {
            return datasetRepository.save(new Dataset(performanceHistory, paymentHistory, farmProductivity, creditRatio, cooperativeMembership));

            //            Dataset dataset = datasetRepository.findLastRecord();

//            classifierAlgorithm.classify(dataset.getId());
        }


    }
}
