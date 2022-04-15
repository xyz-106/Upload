package com.example.pos_report2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.pos_report2.pojo.Child_Shop_Transaction;
import com.example.pos_report2.pojo.Shop_Transaction;

import java.util.ArrayList;
import java.util.List;

public class DbSearch extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Transaction";
    private static final String TB_NAME = "TRANSACTIONS";
    private static final String COL_1 = "TransactionID";
    private static final String COL_2 = "Date";
    private static final String COL_3 = "Phone";
    private static final String COL_4 = "Amount";
    private static final String COL_5 = "Time";
    private static final int DATABASE_VERSION = 1;

    public DbSearch(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TB_NAME + "("
                + COL_1 + " INTEGER PRIMARY KEY," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " INTEGER," + COL_5 + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    public void insertTransaction(Shop_Transaction shop_transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, shop_transaction.getTransactionID());
        values.put(COL_2, shop_transaction.getDate());
        values.put(COL_3, shop_transaction.getPhone_no());
        values.put(COL_4, shop_transaction.getAmount());
        values.put(COL_5, shop_transaction.getTime());
        // Inserting Row
        db.insert(TB_NAME, null, values);
        int Hello;
        String selectCountQuery = "SELECT COUNT (DISTINCT " + COL_2 + ") FROM " + TB_NAME;
        Cursor cursor3 = db.rawQuery(selectCountQuery, null);

        if (cursor3.moveToFirst()) {
            Hello = Integer.parseInt(cursor3.getString(0));

            if (Hello > 45) {
                String selectStartQuery = new StringBuilder().append("SELECT ").append(COL_2).append(" From ").append(TB_NAME)
                        .append(" WHERE " + COL_1 + " =" + " (" + "SELECT MIN" + "(" + COL_1 + ") " + "FROM " + TB_NAME + ")").toString();
                Cursor cursor4 = db.rawQuery(selectStartQuery, null);
                if (cursor4.moveToFirst()) {
                    String str = cursor4.getString(0);
                    db.delete(TB_NAME, COL_2 + " = ?",
                            new String[]{str});
                    db.close();
                }
            }
        }

        db.close(); // Closing database connection
    }

    public List<Shop_Transaction> getAllTransaction() {
        List<Shop_Transaction> transaction_list = new ArrayList<>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shop_Transaction transaction = new Shop_Transaction();
                transaction.setTransactionID(cursor.getString(0));
                transaction.setDate(cursor.getString(1));
                transaction.setPhone_no(cursor.getString(2));
                transaction.setAmount(cursor.getString(3));
                transaction.setTime(cursor.getString(4));

                // Adding contact to list
                transaction_list.add(transaction);
            } while (cursor.moveToNext());
            db.close();
        }
        return transaction_list;
    }

    public List<Child_Shop_Transaction> SearchByDate(String date) {
        Shop_Transaction slt = new Shop_Transaction();
        List<Child_Shop_Transaction> selected_transaction_list = new ArrayList<>();

        String selectQuery = "SELECT " + COL_1 + "," + COL_3 + "," + COL_4 + "," + COL_5 + " FROM " + TB_NAME + " WHERE " + COL_2 + " = " + "'" + date + "'";
        Log.d("QUERY", "" + selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Log.e("Cursor", " " + cursor);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Child_Shop_Transaction transaction = new Child_Shop_Transaction();
                transaction.setTransactionID(cursor.getString(0));
                transaction.setPhone_no(cursor.getString(1));
                transaction.setAmount(Integer.parseInt(cursor.getString(2)));
                transaction.setTime(cursor.getString(3));

                // Adding contact to list
                selected_transaction_list.add(transaction);
            } while (cursor.moveToNext());
            db.close();
        }
        return selected_transaction_list;
    }

    public List<String> FirstRowLastRow2() {
        List<String> selected_transaction_list = new ArrayList<>();
        String selectQuery = new StringBuilder().append("SELECT ").append(COL_2).append(" From ").append(TB_NAME)
                .append(" WHERE " + COL_1 + " =" + " (" + "SELECT MIN" + "(" + COL_1 + ") " + "FROM " + TB_NAME + ")").toString();
        Log.d("QUERY", "" + selectQuery);
        String selectQuery2 = new StringBuilder().append("SELECT ").append(COL_2).append(" From ").append(TB_NAME)
                .append(" WHERE " + COL_1 + " =" + " (" + "SELECT MAX" + "(" + COL_1 + ") " + "FROM " + TB_NAME + ")").toString();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor1 = db.rawQuery(selectQuery, null);
        Cursor cursor2 = db.rawQuery(selectQuery2, null);

        if (cursor1.moveToFirst()) {

            selected_transaction_list.add(cursor1.getString(0));
            // Log.d("I am TranSaction List ", " " + selected_transaction_list);
        }
        if (cursor2.moveToFirst()) {

            selected_transaction_list.add(cursor2.getString(0));
        }
        db.close();
        return selected_transaction_list;
    }

    public void updateTransaction(Shop_Transaction shop_transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID,contact.getID());
        values.put(COL_1, shop_transaction.getTransactionID());
        values.put(COL_2, shop_transaction.getDate());
        values.put(COL_3, shop_transaction.getPhone_no());
        values.put(COL_4, shop_transaction.getAmount());
        values.put(COL_5, shop_transaction.getTime());
        // updating row
        db.update(TB_NAME, values, COL_1 + " = ?",
                new String[]{shop_transaction.getTransactionID()});

        db.close();
    }

    public void deleteTransaction(Shop_Transaction shop_transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TB_NAME, COL_1 + " = ?",
                new String[]{shop_transaction.getTransactionID()});
        db.close();
    }

    public int totalTransaction() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT (" + COL_1 + ") FROM " + TB_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int total_tran = 0;
        if (cursor.moveToFirst()) {
            total_tran = Integer.parseInt(cursor.getString(0));
            db.close();
        }
        return total_tran;
    }

    public int totalAmount() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT SUM (" + COL_4 + ") FROM " + TB_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int total_amount = 0;
        if (cursor.moveToFirst()) {
            total_amount = Integer.parseInt(cursor.getString(0));
            db.close();
        }
        return total_amount;
    }
}
