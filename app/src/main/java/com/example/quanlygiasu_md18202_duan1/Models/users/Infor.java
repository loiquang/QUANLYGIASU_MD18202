package com.example.quanlygiasu_md18202_duan1.Models.users;

import java.util.ArrayList;

public class Infor {

    private int errorCode;
    private String errorMessage;
    private ArrayList<CCCD> data;

    public Infor() {
    }

    public Infor(int errorCode, String errorMessage, ArrayList<CCCD> data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<CCCD> getData() {
        return data;
    }

    public void setData(ArrayList<CCCD> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Infor{" +
                "errorCode=" + errorCode +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
