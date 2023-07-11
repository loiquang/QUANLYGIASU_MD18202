package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.R;

public class HoSoGiaSu extends AppCompatActivity {
private TextView txtTenGV, txtTien, txtSoHS, txtChungChi;
private Toolbar toolbar;
private ImageView imgGV;
private Button btnDangKyGV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so_gia_su);
        toolbar = findViewById(R.id.toolBar);
        txtTenGV = findViewById(R.id.txtTenGV);
        txtTien = findViewById(R.id.txtTien);
        txtSoHS = findViewById(R.id.txtSoHS);
        txtChungChi = findViewById(R.id.txtChungChi);
        imgGV = findViewById(R.id.imgGV);
       btnDangKyGV = findViewById(R.id.btnDangKyGV);
    }
}