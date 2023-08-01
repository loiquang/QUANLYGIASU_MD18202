package com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Teacher_MD implements Serializable {
    private String fullname, dob, educationlevel, email, phone, sex, subject, id, status,  scale, image;
    private int  price, rate;
    private Teacher_MD teacher_md;
    public Teacher_MD(){
    }

    public Teacher_MD(String id, Teacher_MD teacher_md) {
        this.id = id;
        this.teacher_md = teacher_md;
    }

    public Teacher_MD(String dob, String educationlevel, String email, String phone, String fullname, String image, int price, int rate, String scale, String sex, String status, String subject) {
        this.fullname = fullname;
        this.dob = dob;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Teacher_MD getTeacher_md() {
        return teacher_md;
    }

    public void setTeacher_md(Teacher_MD teacher_md) {
        this.teacher_md = teacher_md;
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

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
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
