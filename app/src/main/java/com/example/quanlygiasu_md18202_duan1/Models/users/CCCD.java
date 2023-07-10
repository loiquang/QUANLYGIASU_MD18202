package com.example.quanlygiasu_md18202_duan1.Models.users;

public class CCCD {
    private String address;
    private String dob;
    private String doe;
    private String features;
    private String fullname;
    private String home;
    private String issue_date;
    private String nationality;
    private String sex;

    public CCCD() {
    }

    public CCCD(String address, String dob, String doe, String features, String fullname, String home, String issue_date, String nationality, String sex) {
        this.address = address;
        this.dob = dob;
        this.doe = doe;
        this.features = features;
        this.fullname = fullname;
        this.home = home;
        this.issue_date = issue_date;
        this.nationality = nationality;
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
