package com.revature.entity;

public class subscriptionplans {
        private String PlanName;
        private String Description;
        private Double price;
        private Integer DataLimit;

        private Integer Speed;
        private Integer ValidityPeriod;
        private subscriptions sub;
        private String Ott;
        private String Dth;

    public subscriptionplans() {
    }

    public subscriptionplans(String planName, String description, Double price, Integer dataLimit, Integer speed, Integer validityPeriod, subscriptions sub, String ott, String dth) {
        PlanName = planName;
        Description = description;
        this.price = price;
        DataLimit = dataLimit;
        Speed = speed;
        ValidityPeriod = validityPeriod;
        this.sub = sub;
        Ott = ott;
        Dth = dth;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDataLimit() {
        return DataLimit;
    }

    public void setDataLimit(Integer dataLimit) {
        DataLimit = dataLimit;
    }

    public Integer getSpeed() {
        return Speed;
    }

    public void setSpeed(Integer speed) {
        Speed = speed;
    }

    public Integer getValidityPeriod() {
        return ValidityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        ValidityPeriod = validityPeriod;
    }

    public subscriptions getSub() {
        return sub;
    }

    public void setSub(subscriptions sub) {
        this.sub = sub;
    }

    public String getOtt() {
        return Ott;
    }

    public void setOtt(String ott) {
        Ott = ott;
    }

    public String getDth() {
        return Dth;
    }

    public void setDth(String dth) {
        Dth = dth;
    }
}

