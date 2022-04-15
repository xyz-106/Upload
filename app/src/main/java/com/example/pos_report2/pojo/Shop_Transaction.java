package com.example.pos_report2.pojo;

import android.util.Log;

public class Shop_Transaction {
    String TransactionID;
    String date;
    String phone_no;
    String amount;
    String Time;

    String StartDate;
    String EndDate;

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

    public Shop_Transaction(String StartDate, String EndDate) {
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }



    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Shop_Transaction(String TransactionID, String date, String phone_no, String amount, String Time) {
        this.TransactionID = TransactionID;
        this.date = date;
        this.phone_no = phone_no;
        this.amount = amount;
        this.Time = Time;

        Log.d("HELLO", "" + getEndDate());
        Log.d("HELLO", "" + getStartDate());
    }

    public Shop_Transaction(String TransactionID) {
        this.TransactionID = TransactionID;
    }

    public Shop_Transaction() {

    }

}
