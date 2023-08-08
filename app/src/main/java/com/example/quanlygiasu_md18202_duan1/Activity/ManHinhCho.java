package com.example.quanlygiasu_md18202_duan1.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private int flag = 0;
    private Fragment fragment2 = null;
    private Toolbar toolbar;
    private TextView txtSkip;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);
        //setToolBar
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        //màn hình fragment
        FragmentManager fragment = getSupportFragmentManager();
        fragment2 = new fragment_icon();
        fragment.beginTransaction()
                .replace(R.id.frameLayout, fragment2)
                .commit();
        //ánh xạ nút button and skip
        txtSkip = findViewById(R.id.txtSkip);
        btnNext = findViewById(R.id.btnNext);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
       boolean acti = sharedPreferences.getBoolean("splashShown", false);
       if(acti){
           startActivity(new Intent(ManHinhCho.this, DangNhap.class));
           finish();
       }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment1 = null;
                flag++;
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
                        startActivity(new Intent(ManHinhCho.this, DangNhap.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("splashShown", true);
                        editor.apply();
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
                startActivity(new Intent(ManHinhCho.this, DangNhap.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("splashShown", true);
                editor.apply();
                finish();

            }
        });

    }
}