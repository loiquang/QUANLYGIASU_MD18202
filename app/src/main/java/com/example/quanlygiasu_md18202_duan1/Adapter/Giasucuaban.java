package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class Giasucuaban extends RecyclerView.Adapter<Giasucuaban.ViewHolder> {
    private ArrayList<ReQuestGS> list;

    public Giasucuaban(ArrayList<ReQuestGS> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giasucuaban, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMon.setText(list.get(position).getSubject());
        holder.txtName.setText(list.get(position).getTeacher());

        if (list.get(position).getStatus() == 0) {
            holder.btnTrangThai.setText("Chờ duyệt");
            holder.txtTrangThai.setText("Chờ duyệt" );
            holder.btnTrangThai.setBackgroundResource(R.drawable.layout_text_tien);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMon, txtName, txtTrangThai;
        Button btnTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMon = itemView.findViewById(R.id.txtMon);
            txtName = itemView.findViewById(R.id.txtName);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            btnTrangThai = itemView.findViewById(R.id.btnTrangThai);
        }
    }
}
