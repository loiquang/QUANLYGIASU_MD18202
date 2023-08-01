package com.example.quanlygiasu_md18202_duan1.Models.Request;

public class ReQuestGS {
    private String enddate, startdate,teacher, user, subject, id;
    private int scale, status ;
    private long totalpayment;
    private ReQuestGS reQuestGS;

    public ReQuestGS(String enddate, int scale, String startdate, int status, String subject, String teacher, long totalpayment, String user) {
        this.enddate = enddate;
        this.startdate = startdate;
        this.teacher = teacher;
        this.user = user;
        this.scale = scale;
        this.status = status;
        this.totalpayment = totalpayment;
        this.subject = subject;
    }

    public ReQuestGS getReQuestGS() {
        return reQuestGS;
    }

    public void setReQuestGS(ReQuestGS reQuestGS) {
        this.reQuestGS = reQuestGS;
    }

    public ReQuestGS(){}

    public ReQuestGS(String id, ReQuestGS reQuestGS) {
        this.id = id;
        this.reQuestGS = reQuestGS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(long totalpayment) {
        this.totalpayment = totalpayment;
    }
}
