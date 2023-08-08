package com.example.quanlygiasu_md18202_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class QuanLyUser extends RecyclerView.Adapter<QuanLyUser.ViewHolder> {
    private ArrayList<User> list;
    private Context context;

    public QuanLyUser(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quanly_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
try {
    NumberFormat numberFormat = new DecimalFormat("#,###");
    Glide.with(context).load(list.get(position).getUser().getCccd().getFace()).into(holder.imgUser);
    holder.txtUserN.setText(list.get(position).getUser().getCccd().getName());
    holder.txtSoDu.setText(numberFormat.format(list.get(position).getUser().getMoney())+"vnd");
    holder.txtBD.setText(list.get(position).getUser().getCccd().getDob());
    holder.txtPhone.setText(list.get(position).getUser().getPhone());
    holder.txtAddress.setText(list.get(position).getUser().getCccd().getAddress());
    holder.txtEmail.setText(list.get(position).getUser().getEmail());
    holder.imgDown.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.cardView.setVisibility(View.VISIBLE);
            holder.imgUp.setVisibility(View.VISIBLE);
            holder.imgDown.setVisibility(View.GONE);
        }
    });
    holder.imgUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.cardView.setVisibility(View.GONE);
            holder.imgUp.setVisibility(View.GONE);
            holder.imgDown.setVisibility(View.VISIBLE);
        }
    });

}catch (Exception e){
    holder.txtUserN.setText("Chưa Xác Nhận Thông Tin");
    holder.txtSoDu.setText(list.get(position).getId());
}



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtUserN, txtSoDu, txtBD, txtPhone, txtAddress, txtEmail;
        ImageView imgDown, imgUp, imgUser;
CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserN = itemView.findViewById(R.id.txtUserN);
            txtSoDu = itemView.findViewById(R.id.txtSoDu);
            txtBD = itemView.findViewById(R.id.txtBD);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imgDown = itemView.findViewById(R.id.imgDown);
            imgUp = itemView.findViewById(R.id.imgUp);
            imgUser = itemView.findViewById(R.id.imgUser);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
