package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageButton btnTeacher = findViewById(R.id.btnManagerCoach);
        ImageButton btnUser = findViewById(R.id.btnManagerUser);

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 1;
                connect(flag);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 2;
                connect(flag);
            }
        });

    }
    public  void connect(int flag){
        Intent intent = new Intent(AdminActivity.this, AdminContainerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("flag", flag);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}