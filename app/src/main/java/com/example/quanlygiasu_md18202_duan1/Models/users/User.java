package com.example.quanlygiasu_md18202_duan1.Models.users;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String username;
    private String password;
    private String phone;
    private long money;
    private CCCD cccd;
    private User user;
    private String id;

    public User() {
    }

    public User(CCCD cccd,String email, long money , String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.money = money;
        this.cccd = cccd;
    }
    public User(String id,User user) {
        this.user = user;
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public CCCD getCccd() {
        return cccd;
    }

    public void setCccd(CCCD cccd) {
        this.cccd = cccd;
    }
}
