package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.textfield.TextInputLayout;

public class DangKy extends AppCompatActivity {
private TextInputLayout tilUserName,tilPassWord,tilRePassWord;
private Button btnDangKy;
private TextView txtBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        ImageView imgBack = findViewById(R.id.imgBack);
        tilUserName = findViewById(R.id.tilUserName);
        tilPassWord = findViewById(R.id.tilPassWord);
        tilRePassWord = findViewById(R.id.tilRePassWord);
        btnDangKy = findViewById(R.id.btnDangKy);
        txtBack = findViewById(R.id.txtBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, DangNhap.class));
                finish();
            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, DangNhap.class));
                finish();
            }
        });
    }
}