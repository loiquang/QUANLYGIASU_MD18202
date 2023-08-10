package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.quanlygiasu_md18202_duan1.Fragment2.TienTrinh_fragment;
import com.example.quanlygiasu_md18202_duan1.Fragment2.giasu_fragment;
import com.example.quanlygiasu_md18202_duan1.Fragment2.profile_fragment;
import com.example.quanlygiasu_md18202_duan1.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class ManHinhUser extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    private Fragment fragment;
    private TextView txtTitle;
    private FragmentManager frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_user);
        txtTitle = findViewById(R.id.txtTitle);
        ImageView imgBack = findViewById(R.id.imgBack);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        txtTitle.setText("Gia Sư");
        bottomNavigation = findViewById(R.id.bottomNavigation);
        frameLayout = getSupportFragmentManager();
        fragment = new giasu_fragment();
        frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        bottomNavi(bottomNavigation);
//set nút back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManHinhUser.this, DangNhap.class));
                finish();
            }
        });

    }

    public void bottomNavi(MeowBottomNavigation bottomNavigation) {
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
        int status = sharedPreferences.getInt("status", -1);
        //        Sử dụng meowbottomnavigation
        //đặt mặc định vị trí xuất hiện khi mở lên đầu tiên
        bottomNavigation.show(1, true);
        //đặt id và icon cho từng item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_book_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_calculate_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_person_24));
        //đặt tên cho từng item
        bottomNavigation.setCount(1, "Gia Sư");
        bottomNavigation.setCount(2, "Tiến Trình");
        bottomNavigation.setCount(3, "Profile");
        //gọi onReselect để tránh lỗi null khi bấm lần đầu tiên vào icon mặc định
        bottomNavigation.setOnReselectListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        fragment = new giasu_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();

                return null;
            }
        });
        //lệnh lắng nghe click
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        fragment = new giasu_fragment();
                        txtTitle.setText("Gia Sư");
                        break;
                    case 2:
                        fragment = new TienTrinh_fragment();
                        txtTitle.setText("Tiến Trình");
                        break;
                    case 3:

                            fragment = new profile_fragment();

                        txtTitle.setText("Profile");
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();

                return null;
            }
        });
        //lệnh show ra sau khi lắng nghe click
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 2:
                        fragment = new TienTrinh_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                return null;
            }
        });
        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        fragment = new giasu_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 3:
                            fragment = new profile_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                return null;
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DangNhap.class));
       finish();
    }
}