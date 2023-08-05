package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class TienTrinh extends RecyclerView.Adapter<TienTrinh.ViewHolder> {
    private ArrayList<ReQuestGS> list;
    private Context context;

    public TienTrinh(ArrayList<ReQuestGS> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tien_trinh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenGV.setText(list.get(position).getReQuestGS().getTeacher());
        holder.txtMonHoc.setText(list.get(position).getReQuestGS().getSubject());
        switch (list.get(position).getReQuestGS().getStatus()){
            case 0:
                holder.txtBuoc1.setVisibility(View.VISIBLE);
                holder.txtBuoc1.setTextColor(Color.RED);
                break;
            case 1:
                holder.txtBuoc1.setVisibility(View.VISIBLE);
                holder.txtBuoc2.setVisibility(View.VISIBLE);
                holder.txtBuoc1.setTextColor(Color.GREEN);
                holder.txtBuoc2.setTextColor(Color.RED);
                break;
            case 2:
                holder.txtBuoc1.setVisibility(View.VISIBLE);
                holder.txtBuoc2.setVisibility(View.VISIBLE);
                holder.txtBuoc3.setVisibility(View.VISIBLE);
                holder.txtBuoc1.setTextColor(Color.GREEN);
                holder.txtBuoc2.setTextColor(Color.GREEN);
                holder.txtBuoc3.setTextColor(Color.RED);
                break;
            case 3:
                holder.txtBuoc1.setVisibility(View.VISIBLE);
                holder.txtBuoc2.setVisibility(View.VISIBLE);
                holder.txtBuoc3.setVisibility(View.VISIBLE);
                holder.txtBuoc4.setVisibility(View.VISIBLE);
                holder.txtBuoc1.setTextColor(Color.GREEN);
                holder.txtBuoc2.setTextColor(Color.GREEN);
                holder.txtBuoc3.setTextColor(Color.GREEN);
                holder.txtBuoc4.setTextColor(Color.RED);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenGV, txtMonHoc, txtBuoc1, txtBuoc2, txtBuoc3, txtBuoc4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenGV = itemView.findViewById(R.id.txtTenGV);
            txtMonHoc = itemView.findViewById(R.id.txtMonHoc);
            txtBuoc1 = itemView.findViewById(R.id.buoc1);
            txtBuoc2 = itemView.findViewById(R.id.buoc2);
            txtBuoc3 = itemView.findViewById(R.id.buoc3);
            txtBuoc4 = itemView.findViewById(R.id.buoc4);

        }
    }
}
