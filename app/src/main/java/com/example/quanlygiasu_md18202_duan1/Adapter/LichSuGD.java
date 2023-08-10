package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class LichSuGD extends RecyclerView.Adapter<LichSuGD.ViewHolder> {
    private ArrayList<ReQuestGS> list;
    private Context context;

    public LichSuGD(ArrayList<ReQuestGS> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtIdRQ.setText(list.get(position).getId());
        holder.txtDate.setText(list.get(position).getReQuestGS().getStartdate());
        NumberFormat numberFormat = new DecimalFormat("#,###");
        holder.txtAmount.setText(numberFormat.format(list.get(position).getReQuestGS().getTotalpayment()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdRQ, txtDate, txtAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIdRQ = itemView.findViewById(R.id.txtIdRQ);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtAmount = itemView.findViewById(R.id.txtAmount);
        }
    }
}
