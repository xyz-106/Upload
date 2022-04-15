package com.example.pos_report2.pojo;

import java.util.List;

public class Parent_Shop_Transaction {
    String date;
    List<Child_Shop_Transaction> Child;

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

    public Parent_Shop_Transaction(String StartDate, String EndDate){
        this.StartDate=StartDate;
        this.EndDate=EndDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Child_Shop_Transaction> getChild() {
        return Child;
    }

    public void setChild(List<Child_Shop_Transaction> child) {
        Child = child;
    }

    public Parent_Shop_Transaction(String date, List<Child_Shop_Transaction> Child){
        this.date=date;
        this.Child=Child;

    }
    public Parent_Shop_Transaction(){

    }
}
