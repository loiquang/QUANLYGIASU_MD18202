package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.HopDongActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.PhanHoiActivity;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class Giasucuaban extends RecyclerView.Adapter<Giasucuaban.ViewHolder> {
    private ArrayList<ReQuestGS> list;
    private Context context;

    public Giasucuaban(ArrayList<ReQuestGS> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giasucuaban, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMon.setText(list.get(position).getReQuestGS().getSubject());
        holder.txtName.setText(list.get(position).getReQuestGS().getTeacher());
        Glide.with(context).load(list.get(position).getReQuestGS().getImageTeacher()).into(holder.imgTeacher);
        if (list.get(position).getReQuestGS().getStatus() == 0) {
            holder.btnTrangThai.setText("Chờ duyệt");
            holder.txtTrangThai.setText("Chờ duyệt");
            holder.btnTrangThai.setBackgroundResource(R.drawable.layout_text_tien);
        }
        if (list.get(position).getReQuestGS().getStatus() == 1) {
            holder.btnTrangThai.setText("Ký Hợp Đồng");
            holder.txtTrangThai.setText("Ký Hợp Đồng");
            holder.btnTrangThai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), HopDongActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("nameUser", list.get(holder.getAdapterPosition()).getReQuestGS().getUser());
                    bundle.putString("nameTeacher", list.get(holder.getAdapterPosition()).getReQuestGS().getTeacher());
                    bundle.putString("startDate", list.get(holder.getAdapterPosition()).getReQuestGS().getStartdate());
                    bundle.putString("endDate", list.get(holder.getAdapterPosition()).getReQuestGS().getDate());
                    bundle.putLong("payment", list.get(holder.getAdapterPosition()).getReQuestGS().getTotalpayment());
                    bundle.putString("idHopDong", list.get(holder.getAdapterPosition()).getId());
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });
        }
        if (list.get(position).getReQuestGS().getStatus() == 2) {
            holder.btnTrangThai.setText("Đánh giá");
            holder.btnTrangThai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PhanHoiActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("nameTeacher", list.get(holder.getAdapterPosition()).getReQuestGS().getTeacher());
                    bundle1.putString("subject", list.get(holder.getAdapterPosition()).getReQuestGS().getSubject());
                    intent.putExtras(bundle1);
                    v.getContext().startActivity(intent);
                }
            });
            holder.txtTrangThai.setText("Đang học");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMon, txtName, txtTrangThai;
        Button btnTrangThai;
        ImageView imgTeacher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMon = itemView.findViewById(R.id.txtMon);
            txtName = itemView.findViewById(R.id.txtName);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            btnTrangThai = itemView.findViewById(R.id.btnTrangThai);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
        }
    }
}
