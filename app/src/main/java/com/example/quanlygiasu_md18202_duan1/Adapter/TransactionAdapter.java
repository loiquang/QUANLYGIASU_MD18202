package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.MoMo.Transaction;
import com.example.quanlygiasu_md18202_duan1.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context context;

    ArrayList<Transaction> list;

    public TransactionAdapter(Context context, ArrayList<Transaction> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VNĐ");
        holder.tvPartnerRefId.setText(list.get(position).getPartnerRefId());
        holder.tvDate.setText(list.get(position).getResponseDate());
        holder.tvAmount.setText(decimalFormat.format(list.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPartnerRefId, tvDate, tvAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartnerRefId = itemView.findViewById(R.id.txtIdRQ);
            tvDate = itemView.findViewById(R.id.txtDate);
            tvAmount = itemView.findViewById(R.id.txtAmount);
        }
    }
}
