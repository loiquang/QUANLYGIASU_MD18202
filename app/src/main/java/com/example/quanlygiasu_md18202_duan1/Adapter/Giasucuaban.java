package com.example.quanlygiasu_md18202_duan1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.HopDongActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.PaymentActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.PhanHoiActivity;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.channels.AlreadyBoundException;
import java.util.ArrayList;

public class Giasucuaban extends RecyclerView.Adapter<Giasucuaban.ViewHolder> {
    private ArrayList<ReQuestGS> list;
    private Context context;
    private ArrayList<Teacher_MD> list1;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("isRememberData", MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        holder.txtMon.setText(list.get(position).getReQuestGS().getSubject());
        holder.txtName.setText(list.get(position).getReQuestGS().getTeacher());
        Glide.with(context).load(list.get(position).getReQuestGS().getImageTeacher()).into(holder.imgTeacher);
        if (user.equals("admin")) {
            holder.txtSub.setText("Học Sinh: ");
            holder.txtMon.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            int topPaddingInPixels = 5; // Khoảng cách bên trên, tính theo pixel
            holder.txtMon.setPadding(holder.txtMon.getPaddingLeft(), topPaddingInPixels, holder.txtMon.getPaddingRight(), holder.txtMon.getPaddingBottom());
            holder.txtMon.setText(list.get(position).getReQuestGS().getUser());
            holder.btnTrangThai.setText("Hủy bỏ");
        } else {

            if (list.get(position).getReQuestGS().getStatus() == 0) {
                holder.btnTrangThai.setText("Chờ duyệt");
                holder.btnTrangThai.setBackgroundResource(R.drawable.layout_text_tien);
                holder.txtTextI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.setTitle("Thông tin GV").setMessage("Bạn Chưa Ký Hợp Đồng!").setNegativeButton("OK", null).show();
                    }
                });
            } else if (list.get(position).getReQuestGS().getStatus() == 1) {
                holder.btnTrangThai.setText("Ký Hợp Đồng");
                holder.txtTextI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.setTitle("Thông tin GV").setMessage("Bạn Chưa Ký Hợp Đồng!").setNegativeButton("OK", null).show();
                    }
                });
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
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference2 = firebaseDatabase.getReference().child("user").child("admin").child("money");
                        databaseReference2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                long money1 = snapshot.getValue(long.class);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putLong("moneyAdmin", money1);
                                editor.apply();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                });
            } else if (list.get(position).getReQuestGS().getStatus() == 2) {
                holder.btnTrangThai.setText("Thanh Toán");
                holder.btnTrangThai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                        Bundle bundle = new Bundle();
                        String userName =list.get(holder.getAdapterPosition()).getReQuestGS().getUser();
                        String startDate = list.get(holder.getAdapterPosition()).getReQuestGS().getStartdate();
                        String date = list.get(holder.getAdapterPosition()).getReQuestGS().getDate();
                        long payment = list.get(holder.getAdapterPosition()).getReQuestGS().getTotalpayment();
                        String teacher = list.get(holder.getAdapterPosition()).getReQuestGS().getTeacher();
                        String id = list.get(holder.getAdapterPosition()).getId();
                       ReQuestGS reQuestGS = new ReQuestGS(date,startDate,teacher,userName,id,payment);
                        intent.putExtra("request", reQuestGS);
                        v.getContext().startActivity(intent);

                    }

                });
                holder.txtTextI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.setTitle("Thông tin GV").setMessage("Bạn Chưa Thanh Toán!").setNegativeButton("OK", null).show();
                    }
                });
            } else if (list.get(position).getReQuestGS().getStatus() == 3) {
                holder.btnTrangThai.setText("Đánh giá");
                holder.btnTrangThai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.setTitle("Thông Báo").setMessage("Xác nhận đã hoàn thành khóa học!").setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(v.getContext(), PhanHoiActivity.class);
                                int status = 2;
                                Bundle bundle1 = new Bundle();
                                bundle1.putString("nameTeacher", list.get(holder.getAdapterPosition()).getReQuestGS().getTeacher());
                                bundle1.putString("subject", list.get(holder.getAdapterPosition()).getReQuestGS().getSubject());
                                bundle1.putInt("status", status);
                                intent.putExtras(bundle1);
                                v.getContext().startActivity(intent);
                            }
                        }).setPositiveButton("No", null).show();

                    }
                });
                holder.txtTextI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.setTitle("Thông tin GV").setMessage("SDT: " + list.get(position).getReQuestGS().getPhoneTC() + "\n\nEmail: " + list.get(position).getReQuestGS().getEmailTC()).setNegativeButton("OK", null).show();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMon, txtName, txtTextI, txtSub;
        Button btnTrangThai;
        ImageView imgTeacher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMon = itemView.findViewById(R.id.txtMon);
            txtName = itemView.findViewById(R.id.txtName);
            btnTrangThai = itemView.findViewById(R.id.btnTrangThai);
            imgTeacher = itemView.findViewById(R.id.imgTeacher);
            txtTextI = itemView.findViewById(R.id.txtTextI);
            txtSub= itemView.findViewById(R.id.txtSub);

        }
    }
}
