package com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Teacher_MD implements Serializable {
    private String fullname, dob, educationlevel, email, phone, sex, subject, id, status, scale;
    private int  price, rate, acb;
    public Teacher_MD(){
    }


    public Teacher_MD(String dob, String educationlevel, String email, String phone, String name, int price, int rate, String scale, String sex, String status, String subject) {
        this.fullname = name;
        this.dob = dob;
        this.educationlevel = educationlevel;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.subject = subject;
        this.status = status;
        this.scale = scale;
        this.price = price;
        this.rate = rate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEducationlevel() {
        return educationlevel;
    }

    public void setEducationlevel(String educationlevel) {
        this.educationlevel = educationlevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", fullname);
        return result;
    }
}
