package com.farmCredit.farmCredit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double performanceHistory;
    private double paymentHistory;
    private double farmProductivity;
    private double creditRatio;
    private double cooperativeMembership;
    private double performanceSum;
    private String status;

    public Dataset() {
    }

    public Dataset(double performanceHistory, double paymentHistory, double farmProductivity, double creditRatio, double cooperativeMembership, double performanceSum , String status) {
        this.performanceHistory = performanceHistory;
        this.paymentHistory = paymentHistory;
        this.farmProductivity = farmProductivity;
        this.creditRatio = creditRatio;
        this.cooperativeMembership = cooperativeMembership;
        this.performanceSum = performanceSum;
        this.status = status;
    }

    public Dataset(double performanceHistory, double paymentHistory, double farmProductivity, double creditRatio, double cooperativeMembership, double performanceSum) {
        this.performanceHistory = performanceHistory;
        this.paymentHistory = paymentHistory;
        this.farmProductivity = farmProductivity;
        this.creditRatio = creditRatio;
        this.cooperativeMembership = cooperativeMembership;
        this.performanceSum = performanceSum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPerformanceHistory() {
        return performanceHistory;
    }

    public void setPerformanceHistory(double performanceHistory) {
        this.performanceHistory = performanceHistory;
    }

    public double getFarmProductivity() {
        return farmProductivity;
    }

    public void setFarmProductivity(double farmProductivity) {
        this.farmProductivity = farmProductivity;
    }

    public double getCreditRatio() {
        return creditRatio;
    }

    public void setCreditRatio(double creditRatio) {
        this.creditRatio = creditRatio;
    }

    public double getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(double paymentHistory) {
        this.paymentHistory = paymentHistory;
    }

    public double getCooperativeMembership() {
        return cooperativeMembership;
    }

    public void setCooperativeMembership(double cooperativeMembership) {
        this.cooperativeMembership = cooperativeMembership;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPerformanceSum() {
        return performanceSum;
    }

    public void setPerformanceSum(double performanceSum) {
        this.performanceSum = performanceSum;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "id=" + id +
                ", performanceHistory=" + performanceHistory +
                ", paymentHistory=" + paymentHistory +
                ", farmProductivity=" + farmProductivity +
                ", creditRatio=" + creditRatio +
                ", cooperativeMembership=" + cooperativeMembership +
                ", status='" + status + '\'' +
                '}';
    }
}
