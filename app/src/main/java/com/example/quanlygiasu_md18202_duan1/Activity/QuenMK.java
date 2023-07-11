package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_quenmk;
import com.example.quanlygiasu_md18202_duan1.R;

public class QuenMK extends AppCompatActivity {
private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quen_mk_activity);
        frameLayout = findViewById(R.id.frameLayout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new fragment_quenmk();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }
}