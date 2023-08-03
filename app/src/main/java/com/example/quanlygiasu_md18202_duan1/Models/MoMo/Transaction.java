package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

import java.io.Serializable;

public class Transaction implements Serializable {

    private String partnerRefId;
    private String responseDate;
    private long amount;

    public Transaction() {
    }

    public Transaction(String partnerRefId, String responseDate, long amount) {
        this.partnerRefId = partnerRefId;
        this.responseDate = responseDate;
        this.amount = amount;
    }

    public String getPartnerRefId() {
        return partnerRefId;
    }

    public void setPartnerRefId(String partnerRefId) {
        this.partnerRefId = partnerRefId;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
