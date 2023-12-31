package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlygiasu_md18202_duan1.Activity.HoSoGiaSu;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Teacher_In extends RecyclerView.Adapter<Teacher_In.ViewHolder> {
    private ArrayList<Teacher_MD> list;
    private Context context;

    public Teacher_In(ArrayList<Teacher_MD> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtName.setText(list.get(position).getTeacher_md().getFullname());
        holder.txtMon.setText(list.get(position).getTeacher_md().getStatus());
        Glide.with(context).load(list.get(position).getTeacher_md().getImage()).into(holder.imgTeacher);
        SharedPreferences sharedPreferences = context.getSharedPreferences("isRememberData", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        if (user.equals("admin")) {
            holder.imgRight.setVisibility(View.GONE);
            holder.imgPencil.setVisibility(View.VISIBLE);
            holder.imgPencil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    View view = LayoutInflater.from(context).inflate(R.layout.dialog_trangthai, null);
                    builder.setView(view);
                    EditText edtTrangThai = view.findViewById(R.id.edtText);
                    Button btnOke = view.findViewById(R.id.btnOke);
                    Button btnHuy = view.findViewById(R.id.btnHuy);
                    edtTrangThai.setText(list.get(position).getTeacher_md().getStatus());
                    AlertDialog alertDialog = builder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_dialog);
                    btnOke.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String status = edtTrangThai.getText().toString();
                            if (status.isEmpty()) {
                                Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference().child("teacher").child(list.get(position).getId()).child("status");
                            databaseReference.setValue(status);
                            alertDialog.dismiss();
                        }
                    });
                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });
        }
        holder.imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent intent = new Intent(context, HoSoGiaSu.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", holder.txtName.getText().toString());
                bundle.putString("scale", list.get(holder.getAdapterPosition()).getTeacher_md().getScale());
                bundle.putInt("price", list.get(holder.getAdapterPosition()).getTeacher_md().getPrice());
                bundle.putString("subject", list.get(holder.getAdapterPosition()).getTeacher_md().getSubject());
                bundle.putString("image", list.get(position).getTeacher_md().getImage());
                bundle.putString("id", list.get(holder.getAdapterPosition()).getId());
                bundle.putString("sdt", list.get(holder.getAdapterPosition()).getTeacher_md().getPhone());
                bundle.putString("email", list.get(holder.getAdapterPosition()).getTeacher_md().getEmail());
                intent.putExtras(bundle);
                context.startActivity(intent);
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
        ImageView imgTeacher, imgRight, imgPencil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtMon = itemView.findViewById(R.id.txtMon);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
            imgRight = itemView.findViewById(R.id.imgRight);
            imgPencil = itemView.findViewById(R.id.imgPencil);
        }
    }
}
