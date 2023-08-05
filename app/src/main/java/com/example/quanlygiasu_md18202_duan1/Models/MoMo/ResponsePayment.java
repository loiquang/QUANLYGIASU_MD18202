package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class ResponsePayment {

    private int status;
    private String message;
    private String transid;
    private long amount;
    private String signature;

    public ResponsePayment() {
    }

    public ResponsePayment(int status, String message, String transid, long amount, String signature) {
        this.status = status;
        this.message = message;
        this.transid = transid;
        this.amount = amount;
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

    public String getTransid() {
        return transid;
    }

    public void setTransid(String transid) {
        this.transid = transid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
