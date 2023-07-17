package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.quanlygiasu_md18202_duan1.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ImageButton btnGiaSu = findViewById(R.id.btnManagerCoach);
        ImageButton btnNguoiDung = findViewById(R.id.btnManagerUser);

        btnGiaSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 1;
                connect(flag);
            }
        });
        btnNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = 2;
                connect(flag);
            }
        });
    }

    public void connect(int flag) {
        Intent intent = new Intent(AdminActivity.this, ManagerCoachActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("flag", flag);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}