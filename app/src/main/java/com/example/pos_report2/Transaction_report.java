package com.example.pos_report2;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pos_report2.Adapter.TransactionParentViewAdapter;
import com.example.pos_report2.database.DbSearch;
import com.example.pos_report2.pojo.Child_Shop_Transaction;
import com.example.pos_report2.pojo.Parent_Shop_Transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Transaction_report extends AppCompatActivity {
    TextView start_Date, endDate, revenue, transacion_COUNT;
    Button Search_btn;
    int end_month, start_month, start_year, end_year;
    private static final String TAG = "Month";
    int Start_Day, End_Day = 0;
    int temp_start_day, temp_start_month, temp_start_year;
    int temp_end_day, temp_end_month, temp_end_year;
    DbSearch db;
    String dmy = null;
    RecyclerView mrecyclerview;
    LinearLayoutManager layoutManager;
    List<Parent_Shop_Transaction> parent_shop_transactions = new ArrayList<>();
    String[] items = null;
    TransactionParentViewAdapter parentAdapter;
    List<String> childlist = new ArrayList<>();
    int totalamount;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_java_report);
        start_Date = findViewById(R.id.startDate1);
        endDate = findViewById(R.id.endDate1);

        Search_btn = findViewById(R.id.btnSearch1);
        mrecyclerview = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(layoutManager);


        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        TextView status = findViewById(R.id.titlebar);
        toolbar.setTitle("");
        status.setText("Transaction Report");
        setSupportActionBar(toolbar);

        revenue = findViewById(R.id.total_revenue);
        transacion_COUNT = findViewById(R.id.count);

        Calendar calendar = Calendar.getInstance();
        final int YEAR = calendar.get(Calendar.YEAR);
        final int MONTH = calendar.get(Calendar.MONTH);
        final int DAY = calendar.get(Calendar.DAY_OF_MONTH);
        db = new DbSearch(this);
        int totalcount = db.totalTransaction();
        transacion_COUNT.setText("Transaction Count: " + totalcount);
        Log.d("I am Start Date", " " + start_Date.getText());
        totalamount = db.totalAmount();
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        revenue.setText("Total Revenue: " + myFormat.format(totalamount) + " MMK");
        if (start_Date.getText() == "" && endDate.getText() == "") {
            ShowAll();
        }

        start_Date.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog startdatedialog = new DatePickerDialog(
                                Transaction_report.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        start_year = year;
                                        start_month = month + 1;
                                        String mon = monthMap(start_month);
                                        String s = Integer.toString(dayOfMonth);
                                        Start_Day = Integer.parseInt(s);
                                        if (s.length() == 1) {
                                            s = "0" + dayOfMonth;
                                        }
                                        String date = s + "-" + mon + "-" + year;
                                        start_Date.setText(date);
                                        temp_start_day = Start_Day;
                                        temp_start_month = start_month;
                                        temp_start_year = start_year;
                                    }
                                }, YEAR, MONTH, DAY
                        );
                        startdatedialog.show();

                    }
                }

        );
        endDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog enddatedialog = new DatePickerDialog(
                                Transaction_report.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                        end_year = year;
                                        end_month = month + 1;
                                        String mon = monthMap(end_month);
                                        String s = Integer.toString(dayOfMonth);
                                        End_Day = Integer.parseInt(s);
                                        if (s.length() == 1) {
                                            s = "0" + dayOfMonth;
                                        }
                                        String date = s + "-" + mon + "-" + year;
                                        endDate.setText(date);
                                        temp_end_day = End_Day;
                                        temp_end_month = end_month;
                                        temp_end_year = end_year;
                                    }
                                }, YEAR, MONTH, DAY
                        );
                        enddatedialog.show();
                    }
                }

        );
        Search_btn.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parent_shop_transactions.clear();
                        parentAdapter.notifyDataSetChanged();
                        SearchByDate();

                    }
                }
        );
    }

    public void ShowAll() {
        if ((start_Date.getText() == "" && endDate.getText() != "") ||
                (start_Date.getText() != "" && endDate.getText() == "") || (start_Date.getText() == "" && endDate.getText() == "")) {

            childlist = db.FirstRowLastRow2();
            dbAllSearch();
            SearchByDate();
        }
    }

    public void dbAllSearch() {
        items = childlist.get(0).split("-");
        Start_Day = Integer.parseInt(items[0]);
        start_month = Integer.parseInt(items[1]);
        start_year = Integer.parseInt(items[2]);

        items = childlist.get(1).split("-");
        End_Day = Integer.parseInt(items[0]);
        end_month = Integer.parseInt(items[1]);
        end_year = Integer.parseInt(items[2]);
    }

    public void SearchByDate() {

        int month = 0;
        int year = 0, now_year = 0;
        int i = 0;
        int d = 0;

        now_year = start_year;
        if (start_Date.getText() != "" && endDate.getText() != "") {
            Start_Day = temp_start_day;
            End_Day = temp_end_day;
            start_month = temp_start_month;
            end_month = temp_end_month;
            start_year = temp_start_year;
            end_year = temp_end_year;
        }
        if ((start_Date.getText() != "" && endDate.getText() == "") || (start_Date.getText() == "" && endDate.getText() != "")) {
            dbAllSearch();
        }

        if (start_month > end_month || start_year > end_year || (start_year == end_year && start_month >= end_month && Start_Day > End_Day)) {

            mrecyclerview.getRecycledViewPool().clear();
            parentAdapter.notifyDataSetChanged();
        } else if (((end_month >= start_month) && (end_year > start_year)) || ((end_month <= start_month) && (end_year > start_year))) {

            year = (end_year - start_year);
            month = (year * 12) + end_month;

        } else {
            month = end_month;
        }
        for (i = start_month; i <= month; i++) {
            d = i;
            if (i <= 12) {

                day_month(d, now_year);
                if (i == 12) {
                    now_year = now_year + 1;
                }


            } else if (i > 12) {
                if (i % 12 == 0) {
                    d = 12;
                    day_month(d, now_year);
                    if (d == 12) {
                        now_year = now_year + 1;
                    }

                } else {
                    d = (i % 12);
                    day_month(d, now_year);

                }
            }

        }

    }

    public void day_month(int d, int now_year) {
        int s = month_total_date_count(d, now_year);
        int start = 0;
        if (start_month == end_month && start_year == end_year) {
            for (int k = Start_Day; k <= End_Day; k++) {
                dmy = k + "-" + d + "-" + now_year;
                ParentItemList();
            }
        } else if (d == end_month && end_year == now_year) {
            s = End_Day;
            for (int k = 1; k <= s; k++) {
                dmy = k + "-" + d + "-" + now_year;

                ParentItemList();

            }
        } else if (d == start_month && now_year == start_year) {
            start = Start_Day;
            for (int k = start; k <= s; k++) {
                dmy = k + "-" + d + "-" + now_year;

                ParentItemList();
            }
        } else {
            for (int k = 1; k <= s; k++) {
                dmy = k + "-" + d + "-" + now_year;

                ParentItemList();
            }
        }
    }

    public int month_total_date_count(int month, int now_year) {
        int total_day = 0;
        if (month == 9 || month == 4 || month == 6 || month == 11) {
            total_day = 30;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            total_day = 31;
        } else if (month == 2) {
            if (now_year % 4 == 0) {
                total_day = 29;
            } else {
                total_day = 28;
            }
        }
        return total_day;
    }

    private void ParentItemList() {

        if (!Child_Shop_Transaction().isEmpty() && dmy != null) {
            Parent_Shop_Transaction parent_shop_transaction = new Parent_Shop_Transaction(dmy, Child_Shop_Transaction());
            parent_shop_transactions.add(parent_shop_transaction);
        }
        parentAdapter = new TransactionParentViewAdapter(parent_shop_transactions, this);
        mrecyclerview.setAdapter(parentAdapter);
        //return parent_shop_transactions;

    }

    private List<Child_Shop_Transaction> Child_Shop_Transaction() {
        // Log.d("TAG ", "I am dmy " + dmy);
        List<Child_Shop_Transaction> child_shop_transactions = new ArrayList<>();
        List<Child_Shop_Transaction> childShopTransactions = null;
        if (dmy != null) {
            childShopTransactions = db.SearchByDate(dmy);
        }

        return childShopTransactions;
    }

    public String monthMap(int month) {
        String Month = null;
        switch (month) {
            case 1:
                Month = "Jan";
                break;
            case 2:
                Month = "Feb";
                break;
            case 3:
                Month = "Mar";
                break;
            case 4:
                Month = "Apr";
                break;
            case 5:
                Month = "May";
                break;
            case 6:
                Month = "Jun";
                break;
            case 7:
                Month = "July";
                break;
            case 8:
                Month = "Aug";
                break;
            case 9:
                Month = "Sep";
                break;
            case 10:
                Month = "Oct";
                break;
            case 11:
                Month = "Nov";
                break;
            case 12:
                Month = "Dec";
                break;
            default:
                Month = Integer.toString(month);
                break;
        }
        return Month;
    }


}

