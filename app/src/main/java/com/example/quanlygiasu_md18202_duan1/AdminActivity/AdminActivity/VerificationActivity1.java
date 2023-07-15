package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.quanlygiasu_md18202_duan1.R;

public class VerificationActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification1);

        ImageView ivCMNDtruoc = findViewById(R.id.ivCMNDtruoc);
        Button btnCMNDtruoc = findViewById(R.id.btnCMNDtruoc);

        btnCMNDtruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút được nhấn nút CMND trước

            }
        });
        ImageView ivCMNDsau = findViewById(R.id.ivCMNDsau);
        Button btnCMNDsau = findViewById(R.id.btnCMNDsau);

        btnCMNDsau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút được nhấn nút CMND sau
            }
        });
    }
}