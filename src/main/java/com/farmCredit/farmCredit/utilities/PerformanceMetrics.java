package com.farmCredit.farmCredit.utilities;

import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PerformanceMetrics {

    public static double farmProductivity(double farmSize, double farmOutput){
        return farmSize / farmOutput;
    }

    public static double creditRating(long loanCount, double totalLoanValue){
        return loanCount / totalLoanValue;
    }

    public static double paymentHistoryVolume(long totalVolume, long returnedCashVolume, long returnedGrainVolume){
        double grain = 1.00 * returnedGrainVolume / totalVolume;
        double cash = .5 * returnedCashVolume / totalVolume;

        return grain + cash;
    }

    public static double paymentHistoryValue(long totalValue, long returnedCashValue, long returnedGrainValue){
        double grain = 1.00 * returnedGrainValue / totalValue;
        double cash = .5 * returnedCashValue / totalValue;

        return grain + cash;
    }

    public static double duration(List<Map<String, Date>> loanDates){

        Calendar approval = Calendar.getInstance();
        Calendar expected = Calendar.getInstance();
        Calendar actual = Calendar.getInstance();

        double sum = 0.0;

        for (Map<String, Date> runDates : loanDates){
            approval.setTime(runDates.get("approval"));
            expected.setTime(runDates.get("expected"));
            actual.setTime(runDates.get("actual"));
            long expectedCount = expected.get(Calendar.MONTH) - approval.get(Calendar.MONTH);
            long actualCount = actual.get(Calendar.MONTH) - approval.get(Calendar.MONTH);
            sum += (expectedCount) / (actualCount);

            LoggerFactory.getLogger(PerformanceMetrics.class).info("expectedDate: " + expectedCount);
            LoggerFactory.getLogger(PerformanceMetrics.class).info("ActualDate: " + actualCount);
            LoggerFactory.getLogger(PerformanceMetrics.class).info("Sum: " + sum);
        }

        return sum;
    }

    public static double performanceHistory(List<Map<String, Double>> performance){
        double sum = 0.0;

        for (Map<String, Double> perf: performance){
            sum += (perf.get("actual") / perf.get("expected"));
        }

        return sum;
    }

}
