package com.example.pos_report2.pojo;

public class Child_Shop_Transaction {
    String TransactionID;
    String phone_no;
    int amount;
    String Time;
    String Date;

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    String StartDate;
    String EndDate;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Child_Shop_Transaction(String Transaction_ID, String Date, int amount, String phone_no, String Time) {
        this.TransactionID = Transaction_ID;
        this.phone_no = phone_no;
        this.Time = Time;
        this.Date = Date;
        this.amount = amount;
    }

    public Child_Shop_Transaction() {

    }

    public Child_Shop_Transaction(String Transaction_ID, String Phone_no, int amount, String Time) {
        this.TransactionID = Transaction_ID;
        this.phone_no = Phone_no;
        this.amount = amount;
        this.Time = Time;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

}
