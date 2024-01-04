package com.revature.entity;

public class user {
    private int UserID;
    private String Username;
    private String Password;
    private String  PhoneNumber;
    private String Email;
    private String Address;
    private boolean Act;
    private subscriptions plan;

    public subscriptions getPlan() {
        return plan;
    }

    public void setPlan(subscriptions plan) {
        this.plan = plan;
    }

    public boolean Act() {
        return Act;
    }

    public void setAct(boolean Act) {
        Act = Act;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }


    public void setPassword(String password) {
        Password = password;
    }

    public user(String username, String password, String phoneNumber, String email, String address) {
        Username = username;
        Password = password;
        PhoneNumber = phoneNumber;
        Email = email;
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

}
