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
private EditText edtUserName, edtPassWord;
private TextView txtDangKy,txtQuenMK;
private Button btnDangNhap;
private CheckBox checkRMB;
private ImageView imgFace, imgInsta, imgGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        txtDangKy = findViewById(R.id.txtDangKy);
        txtQuenMK = findViewById(R.id.txtQuenMK);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        checkRMB = findViewById(R.id.checkRMB);
        imgFace = findViewById(R.id.imgFace);
        imgInsta = findViewById(R.id.imgInsta);
        imgGoogle = findViewById(R.id.imgGoogle);

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });
    }
}