package com.example.quanlygiasu_md18202_duan1.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_chatluonghoctap;
import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_icon;
import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_timkiemgiasu;
import com.example.quanlygiasu_md18202_duan1.R;
import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_hoctainhavui;

public class ManHinhCho extends AppCompatActivity {
    int flag = 0;
    Fragment fragment2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);
        //setToolBar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        //màn hình fragment
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        FragmentManager fragment = getSupportFragmentManager();
        fragment2 = new fragment_icon();
        fragment.beginTransaction()
                .replace(R.id.frameLayout, fragment2)
                .commit();
        //ánh xạ nút button and skip
        TextView txtSkip = findViewById(R.id.txtSkip);
        Button btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = null;
                flag++;
                Toast.makeText(ManHinhCho.this, "" + flag, Toast.LENGTH_SHORT).show();
                switch (flag) {
                    case 1:
                        fragment1 = new fragment_hoctainhavui();
                        break;
                    case 2:
                        fragment1 = new fragment_timkiemgiasu();
                        break;
                    case 3:
                        fragment1 = new fragment_chatluonghoctap();
                        btnNext.setText("Bắt Đầu");
                        break;
                    case 4:
                        startActivity(new Intent(ManHinhCho.this, MainActivity.class));
                        finish();
                        break;
                    default:
                        break;

                }
                if (fragment1 != null)
                    fragment.beginTransaction()
                            .replace(R.id.frameLayout, fragment1)
                            .commit();

            }
        });
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManHinhCho.this, MainActivity.class));
                finish();

            }
        });

    }
}