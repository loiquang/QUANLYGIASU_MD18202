package com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models;

public class MonHoc_User_Models {
    private int image;
    private String name;

    public MonHoc_User_Models(int image, String name) {
        this.image = image;
        this.name = name;
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

}
