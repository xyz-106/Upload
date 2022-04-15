package com.example.pos_report2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.pos_report2.database.DbSearch;
import com.example.pos_report2.pojo.Shop_Transaction;

import java.util.List;

public class DataInsertionForTransaction extends AppCompatActivity {

    DbSearch db;
    EditText editTranID, editDate, editPhone, editAmount, editTime;
    Button btnAddData, btnViewALl, btnUpdate, btnDelete, btnclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insertion_for_transaction);

        editTranID = (EditText) findViewById(R.id.Transaction_ID);
        editDate = (EditText) findViewById(R.id.date);
        editPhone = (EditText) findViewById(R.id.Phone_No);
        editAmount = (EditText) findViewById(R.id.Amount);
        editTime = (EditText) findViewById(R.id.Time);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewALl = (Button) findViewById(R.id.button_view);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);
        btnclear = (Button) findViewById(R.id.button_clear);
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        TextView status = findViewById(R.id.titlebar);
        toolbar.setTitle("");
        status.setText("Data Insertion For Report");
        setSupportActionBar(toolbar);
        db = new DbSearch(this);
        AddData();
        viewStudentAll();
        UpdateData();
        DeleteData();
        ClearData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.insertTransaction(new Shop_Transaction(
                                editTranID.getText().toString(),
                                editDate.getText().toString(),
                                editPhone.getText().toString(),
                                editAmount.getText().toString(),
                                editTime.getText().toString()
                        ));
                    }
                }
        );
    }

    public void viewStudentAll() {
        btnViewALl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<Shop_Transaction> total_tran = db.getAllTransaction();
                        StringBuffer buffer = new StringBuffer();

                        for (Shop_Transaction tran : total_tran) {
                            buffer.append("Tran ID      " + tran.getTransactionID() + "\n");
                            buffer.append("Tran Date  " + tran.getDate() + "\n");
                            buffer.append("Phone        " + tran.getPhone_no() + "\n");
                            buffer.append("Amount     " + tran.getAmount() + "\n");
                            buffer.append("Time           " + tran.getTime() + "\n\n");
                        }
                        ShowData("Transaction Data", buffer.toString());
                    }
                }
        );
    }

    public void ShowData(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.updateTransaction(new Shop_Transaction(
                                editTranID.getText().toString(),
                                editDate.getText().toString(),
                                editPhone.getText().toString(),
                                editAmount.getText().toString(),
                                editTime.getText().toString()
                        ));
                    }
                }
        );
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.deleteTransaction(
                                new Shop_Transaction(
                                        editTranID.getText().toString()
                                )
                        );
                    }
                }
        );
    }

    public void ClearData(){
        btnclear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTranID.setText("");
                        editDate.setText("");
                        editAmount.setText("");
                        editPhone.setText("");
                        editTime.setText("");
                    }
                }
        );
    }

}