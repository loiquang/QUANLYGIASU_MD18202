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

public class DangKy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        ImageView imgBack = findViewById(R.id.imgBack);
        EditText edtUserName = findViewById(R.id.edtUserName);
        EditText edtPassWord = findViewById(R.id.edtPassWord);
        EditText edtRePassWord = findViewById(R.id.edtRePassWord);
        Button btnDangKy = findViewById(R.id.btnDangKy);
        TextView txtBack = findViewById(R.id.txtBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, MainActivity.class));
                finish();
            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, MainActivity.class));
                finish();
            }
        });
    }
}