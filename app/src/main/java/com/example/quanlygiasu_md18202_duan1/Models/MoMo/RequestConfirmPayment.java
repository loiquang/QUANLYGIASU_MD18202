package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class RequestConfirmPayment {

    private String partnerCode;
    private String partnerRefId;
    private String requestType;
    private String requestId;
    private String momoTransId;
    private String signature;

    public RequestConfirmPayment() {
    }

    public RequestConfirmPayment(String partnerCode, String partnerRefId, String requestType, String requestId, String momoTransId, String signature) {
        this.partnerCode = partnerCode;
        this.partnerRefId = partnerRefId;
        this.requestType = requestType;
        this.requestId = requestId;
        this.momoTransId = momoTransId;
        this.signature = signature;
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMomoTransId() {
        return momoTransId;
    }

    public void setMomoTransId(String momoTransId) {
        this.momoTransId = momoTransId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
