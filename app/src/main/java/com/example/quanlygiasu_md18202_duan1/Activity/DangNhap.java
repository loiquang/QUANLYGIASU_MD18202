package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.R;

public class DangNhap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        EditText edtUserName = findViewById(R.id.edtUserName);
        EditText edtPassWord = findViewById(R.id.edtPassWord);
        TextView txtDangKy = findViewById(R.id.txtDangKy);
        TextView txtQuenMK = findViewById(R.id.txtQuenMK);
        Button btnDangNhap = findViewById(R.id.btnDangNhap);
        CheckBox checkRMB = findViewById(R.id.checkRMB);
        ImageView imgFace = findViewById(R.id.imgFace);
        ImageView imgInsta = findViewById(R.id.imgInsta);
        ImageView imgGoogle = findViewById(R.id.imgGoogle);

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });
    }
}