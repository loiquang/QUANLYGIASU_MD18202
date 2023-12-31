package com.example.quanlygiasu_md18202_duan1.Models.users;

import java.io.Serializable;

public class CCCD implements Serializable {
    private String address;
    private String id;
    private String name;
    private String type;
    private String dob;
    private String issue_date;
    private String sex;
    private String face;
  

    public CCCD() {
    }



    public CCCD(String id, String name, String sex, String dob, String address, String issue_date, String face, String type) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.address = address;
        this.issue_date = issue_date;
        this.face = face;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CCCD{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dob='" + dob + '\'' +
                ", address='" + address + '\'' +
                ", issue_date='" + issue_date + '\'' +
                ", face='" + face + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
