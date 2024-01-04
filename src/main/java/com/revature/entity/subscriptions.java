package com.revature.entity;

import java.util.Date;

public class subscriptions {
    private int id;
    private int userId;  // Foreign key referencing User
    private String planName;
    private double price;
    private Date startDate;


//    public subscriptions(int userId, String planName, double price, java.sql.Date startDate) {
//    }

    public subscriptions(int userId, String planName, double price, Date startDate) {
        this.userId=userId;
        this.planName = planName;
        this.price = price;
        this.startDate = startDate;
    }
// Constructors, getters, and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // ...
}
