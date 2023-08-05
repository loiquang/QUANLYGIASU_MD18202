package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class DataResponseConfirmPayment {

    private String partnerCode;
    private String momoTransId;
    private long amount;
    private String partnerRefId;

    public DataResponseConfirmPayment() {
    }

    public DataResponseConfirmPayment(String partnerCode, String momoTransId, long amount, String partnerRefId) {
        this.partnerCode = partnerCode;
        this.momoTransId = momoTransId;
        this.amount = amount;
        this.partnerRefId = partnerRefId;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getMomoTransId() {
        return momoTransId;
    }

    public void setMomoTransId(String momoTransId) {
        this.momoTransId = momoTransId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPartnerRefId() {
        return partnerRefId;
    }

    public void setPartnerRefId(String partnerRefId) {
        this.partnerRefId = partnerRefId;
    }
}
