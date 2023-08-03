package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class RequestQueryStatus {

    private String partnerCode;
    private String partnerRefId;
    private String hash;
    private double version;

    public RequestQueryStatus() {
    }

    public RequestQueryStatus(String partnerCode, String partnerRefId, String hash, double version) {
        this.partnerCode = partnerCode;
        this.partnerRefId = partnerRefId;
        this.hash = hash;
        this.version = version;
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
