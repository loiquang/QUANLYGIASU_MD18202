package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class DataResponseQueryStatus {
    private int status;
    private String message;
    private String partnerCode;
    private String billId;
    private int transid;
    private long amount;
    private long discountAmount;
    private long fee;
    private String phoneNumber;
    private String customerName;
    private String storeId;
    private String requestDate;
    private String responseDate;

    public DataResponseQueryStatus() {
    }

    public DataResponseQueryStatus(int status, String message, String partnerCode, String billId, int transid, long amount, long discountAmount, long fee, String phoneNumber, String customerName, String storeId, String requestDate, String responseDate) {
        this.status = status;
        this.message = message;
        this.partnerCode = partnerCode;
        this.billId = billId;
        this.transid = transid;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.fee = fee;
        this.phoneNumber = phoneNumber;
        this.customerName = customerName;
        this.storeId = storeId;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
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

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getTransid() {
        return transid;
    }

    public void setTransid(int transid) {
        this.transid = transid;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }
}
