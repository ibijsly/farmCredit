package com.farmCredit.farmCredit.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Farmer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fullname;
    private String farmName;
    private String farmLocation;
    private Double farmSize;
    private Double averageOutput;

    private String farmState;

    @OneToMany
    @JoinColumn(name = "cooperative_id", referencedColumnName = "id")
    private Cooperative cooperativeId;

    private Date regDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public String getFarmState() {
        return farmState;
    }

    public void setFarmState(String farmState) {
        this.farmState = farmState;
    }

    public Cooperative getCooperativeId() {
        return cooperativeId;
    }

    public void setCooperativeId(Cooperative cooperativeId) {
        this.cooperativeId = cooperativeId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Double getFarmSize() {
        return farmSize;
    }

    public void setFarmSize(Double farmSize) {
        this.farmSize = farmSize;
    }

    public Double getAverageOutput() {
        return averageOutput;
    }

    public void setAverageOutput(Double averageOutput) {
        this.averageOutput = averageOutput;
    }
}
