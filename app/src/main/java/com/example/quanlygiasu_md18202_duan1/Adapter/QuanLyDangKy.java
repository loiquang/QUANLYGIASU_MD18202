package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.HopDongActivity;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class QuanLyDangKy extends RecyclerView.Adapter<QuanLyDangKy.ViewHolder> {
    private ArrayList<ReQuestGS> list;

    public QuanLyDangKy(ArrayList<ReQuestGS> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quanly_dangky, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtStartDate.setText(list.get(position).getReQuestGS().getStartdate());
        holder.txtEndDate.setText(list.get(position).getReQuestGS().getDate());
        holder.txtScale.setText("" + list.get(position).getReQuestGS().getScale());
        holder.txtSubject.setText(list.get(position).getReQuestGS().getSubject());
        holder.txtTeacher.setText(list.get(position).getReQuestGS().getTeacher());
        holder.txtUser.setText(list.get(position).getReQuestGS().getUser());
        NumberFormat numberFormat = new DecimalFormat("#,###");
        holder.txtPayment.setText(numberFormat.format(list.get(holder.getAdapterPosition()).getReQuestGS().getTotalpayment()));
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("request");
        GetListFireBase getListFireBase = new GetListFireBase();
        getListFireBase.readDatabase3(databaseReference, new Interface_list() {
            ArrayList<ReQuestGS> list1 = new ArrayList<>();

            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {
            }

            @Override
            public void onListReceived1(ArrayList<User> list) {
            }

            @Override
            public void onListReceived2(ArrayList<ReQuestGS> list) {
                for (ReQuestGS reQuestGS : list) {
                    if (reQuestGS.getReQuestGS().getStatus() == 0) {
                        list1.add(reQuestGS);
                    }

                }

                    holder.btnDuyet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String key = list1.get(position).getId();
                            databaseReference.child(key).child("status").setValue(1);

                    }});



            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStartDate, txtEndDate, txtScale, txtSubject, txtTeacher, txtUser, txtPayment;
        Button btnDuyet, btnHuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStartDate = itemView.findViewById(R.id.txtStartDate);
            txtEndDate = itemView.findViewById(R.id.txtEndDate);
            txtScale = itemView.findViewById(R.id.txtScale);
            txtSubject = itemView.findViewById(R.id.txtSubject);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            txtUser = itemView.findViewById(R.id.txtUser);
            txtPayment = itemView.findViewById(R.id.txtPayment);
            btnDuyet = itemView.findViewById(R.id.btnDuyet);
        }
    }
}
