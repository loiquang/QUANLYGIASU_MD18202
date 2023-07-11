package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_In_Models;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class Teacher_In extends RecyclerView.Adapter<Teacher_In.ViewHolder>  {
    private ArrayList<Teacher_In_Models> list;

    private MonHoc_User_Models monHoc_user;

    public Teacher_In(ArrayList<Teacher_In_Models> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgTeacher.setImageResource(list.get(position).getImage());
        holder.txtName.setText(list.get(position).getName());
        holder.txtMon.setText(list.get(position).getJob());
        holder.imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtMon;
        ImageView imgTeacher, imgRight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMon = itemView.findViewById(R.id.txtMon);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
            imgRight = itemView.findViewById(R.id.imgRight);
        }
    }
}
