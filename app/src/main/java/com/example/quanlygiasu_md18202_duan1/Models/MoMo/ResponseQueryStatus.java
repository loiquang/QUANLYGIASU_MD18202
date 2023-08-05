package com.example.quanlygiasu_md18202_duan1.Models.MoMo;

public class ResponseQueryStatus {

    private int status;
    private String message;
    private DataResponseQueryStatus data;

    public ResponseQueryStatus() {
    }

    public ResponseQueryStatus(int status, String message, DataResponseQueryStatus data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public DataResponseQueryStatus getData() {
        return data;
    }

    public void setData(DataResponseQueryStatus data) {
        this.data = data;
    }
}
