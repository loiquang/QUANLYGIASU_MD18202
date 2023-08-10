package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlygiasu_md18202_duan1.Models.Request.HoaDon;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class HoaDonRC extends RecyclerView.Adapter<HoaDonRC.ViewHolder> {
private ArrayList<HoaDon> list ;
private Context context;

    public HoaDonRC(ArrayList<HoaDon> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImage()).into(holder.imgHD);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

ImageView imgHD;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHD = itemView.findViewById(R.id.imgHD);
        }
    }
}
