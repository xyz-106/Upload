package com.example.pos_report2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pos_report2.R;
import com.example.pos_report2.pojo.Parent_Shop_Transaction;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

public class TransactionParentViewAdapter extends RecyclerView.Adapter<TransactionParentViewAdapter.TransactionParentHolder> {

    List<Parent_Shop_Transaction> parent_shop_transactionList;
    Context context;

    public TransactionParentViewAdapter(List<Parent_Shop_Transaction> parent_shop_transactions, Context context) {
        this.parent_shop_transactionList = parent_shop_transactions;
        this.context = context;
    }

    @NonNull

    public TransactionParentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_parent_adapter, parent, false);
        return new TransactionParentHolder(view);
    }


    public void onBindViewHolder(@NonNull TransactionParentHolder holder, int position) {
        Parent_Shop_Transaction parent_shop_transaction = parent_shop_transactionList.get(position);
        holder.tranDate.setText(parent_shop_transaction.getDate());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(parent_shop_transaction.getChild().size());
        TransactionChildViewAdapter transactionChildViewAdapter = new TransactionChildViewAdapter(parent_shop_transaction.getChild(), this);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setAdapter(transactionChildViewAdapter);
    }

    @Override
    public int getItemCount() {
        return parent_shop_transactionList.size();
    }

    public class TransactionParentHolder extends RecyclerView.ViewHolder {
        TextView tranDate;
        RecyclerView childRecyclerView;

        public TransactionParentHolder(@NonNull View itemView) {
            super(itemView);
            tranDate = itemView.findViewById(R.id.Transaction_Date);
            childRecyclerView=itemView.findViewById(R.id.child_recycler_view);
            childRecyclerView.addItemDecoration(
                    new HorizontalDividerItemDecoration.Builder(childRecyclerView.getContext())
                            .margin(5, 5)
                            .build());
        }
    }
}
