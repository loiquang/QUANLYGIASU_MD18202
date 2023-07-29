package com.example.quanlygiasu_md18202_duan1.Models.users;

public class User {
    private String email;
    private String username;
    private String password;
    private String phone;
    private long money;
    private CCCD cccd;

    public User() {
    }

    public User(String email, String username, String password, String phone, long money, CCCD cccd) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.money = money;
        this.cccd = cccd;
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
