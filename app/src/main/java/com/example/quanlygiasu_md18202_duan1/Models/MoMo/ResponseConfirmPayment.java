package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class ResponseConfirmPayment {

    private int status;
    private String message;
    private DataResponseConfirmPayment data;
    private String signature;

    public ResponseConfirmPayment() {
    }

    public ResponseConfirmPayment(int status, String message, DataResponseConfirmPayment data, String signature) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.signature = signature;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataResponseConfirmPayment getData() {
        return data;
    }

    public void setData(DataResponseConfirmPayment data) {
        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
