package com.example.pos_report2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pos_report2.R;
import com.example.pos_report2.pojo.Child_Shop_Transaction;

import java.text.NumberFormat;
import java.util.List;

public class TransactionChildViewAdapter extends RecyclerView.Adapter<TransactionChildViewAdapter.ChildViewHolder> {
    List<Child_Shop_Transaction> ChildShopTransaction;
    TransactionParentViewAdapter parentViewAdapter;

    TransactionChildViewAdapter(List<Child_Shop_Transaction> ChildShopTran, TransactionParentViewAdapter parentViewAdapter) {
        this.ChildShopTransaction = ChildShopTran;
        this.parentViewAdapter = parentViewAdapter;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_child_adapter, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        Child_Shop_Transaction child_shop_transaction = ChildShopTransaction.get(position);
        holder.TranID.setText(child_shop_transaction.getTransactionID());
        holder.TranPhone.setText("From - "+child_shop_transaction.getPhone_no());
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        int i=child_shop_transaction.getAmount();
        holder.TranAmount.setText("+" + myFormat.format(i)+ ".00 MMK");
        holder.TranTime.setText(child_shop_transaction.getTime());
        //holder.TranDate.setText(child_shop_transaction.getDate());
    }

    @Override
    public int getItemCount() {
        return ChildShopTransaction.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        TextView TranID, TranPhone, TranAmount, TranTime, TranDate;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
            TranID = itemView.findViewById(R.id.tran_ID);
            TranPhone = itemView.findViewById(R.id.tran_phone);
            TranAmount = itemView.findViewById(R.id.tran_amount);
            TranTime = itemView.findViewById(R.id.tran_time);
            //TranDate=itemView.findViewById(R.id.tran_dates);
        }
    }
}
