package com.example.quanlygiasu_md18202_duan1.Models;

public class MonHoc_User {
    private int image;
    private String name;
    private String job;

    public MonHoc_User(int image, String name, String job) {
        this.image = image;
        this.name = name;
        this.job = job;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
