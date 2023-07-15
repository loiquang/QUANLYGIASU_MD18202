package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.Fragment2.dangky_giasu_fragment;
import com.example.quanlygiasu_md18202_duan1.R;

import java.nio.charset.StandardCharsets;

public class HoSoGiaSu extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout frameLayout;
   private TextView txtTenGV;
   private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so_gia_su);
        toolbar =findViewById(R.id.toolBar);
        txtTenGV = findViewById(R.id.txtTenGV);
        imgBack = findViewById(R.id.imgBack);
        Fragment fragment = new dangky_giasu_fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutGV, fragment).commit();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        Bundle bundle = getIntent().getExtras();
        String tenGV = bundle.getString("name");
        txtTenGV.setText(tenGV);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoSoGiaSu.this, ManHinhUser.class));
                finish();
            }
        });
    }
}