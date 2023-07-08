package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Models.Teacher_In;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {
    private ArrayList<Teacher_In> list;

    public TeacherAdapter(ArrayList<Teacher_In> list) {
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtMon;
        ImageView imgTeacher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMon = itemView.findViewById(R.id.txtMon);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
        }
    }
}
