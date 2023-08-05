package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class RequestPayment {

    private String partnerCode;
    private String partnerRefId;
    private String customerNumber;
    private String appData;
    private String hash;
    private double version;
    private int payType;

    public RequestPayment() {
    }

    public RequestPayment(String partnerCode, String partnerRefId, String customerNumber, String appData, String hash, double version, int payType) {
        this.partnerCode = partnerCode;
        this.partnerRefId = partnerRefId;
        this.customerNumber = customerNumber;
        this.appData = appData;
        this.hash = hash;
        this.version = version;
        this.payType = payType;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerRefId() {
        return partnerRefId;
    }

    public void setPartnerRefId(String partnerRefId) {
        this.partnerRefId = partnerRefId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAppData() {
        return appData;
    }

    public void setAppData(String appData) {
        this.appData = appData;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }
}
