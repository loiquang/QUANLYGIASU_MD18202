package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.quanlygiasu_md18202_duan1.Adapter.UserAdapter;
import com.example.quanlygiasu_md18202_duan1.Fragment2.giasu_fragment;
import com.example.quanlygiasu_md18202_duan1.Fragment2.combo_fragment;
import com.example.quanlygiasu_md18202_duan1.Fragment2.profile_fragment;
import com.example.quanlygiasu_md18202_duan1.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ManHinhUser extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;
    private Fragment fragment;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_user);
        TextView txtTitle = findViewById(R.id.txtTitle);
        ImageView imgBack = findViewById(R.id.imgBack);
        Toolbar toolbar = findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        txtTitle.setText("Gia Sư");
        bottomNavigation = findViewById(R.id.bottomNavigation);
        FragmentManager frameLayout = getSupportFragmentManager();
        fragment = new giasu_fragment();
        frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        //Sử dụng meowbottomnavigation
        //đặt mặc định vị trí xuất hiện khi mở lên đầu tiên
        bottomNavigation.show(1, true);
        //đặt id và icon cho từng item
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_book_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_calculate_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_person_24));
        //đặt tên cho từng item
        bottomNavigation.setCount(1, "Gia Sư");
        bottomNavigation.setCount(2, "Combo");
        bottomNavigation.setCount(3, "Profile");
        //gọi onReselect để tránh lỗi null khi bấm lần đầu tiên vào icon mặc định
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:
                        fragment = new giasu_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }

        });
        //lệnh lắng nghe click
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

                switch (item.getId()) {
                    case 1:
                        fragment = new giasu_fragment();
                        txtTitle.setText("Gia Sư");
                        break;
                    case 2:
                        fragment = new combo_fragment();
                        txtTitle.setText("Combo");
                        break;
                    case 3:
                        fragment = new profile_fragment();
                        txtTitle.setText("Profile");
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });
        //lệnh show ra sau khi lắng nghe click
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                switch (item.getId()) {
                    case 2:
                        fragment = new combo_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                switch (item.getId()) {
                    case 1:
                        fragment = new giasu_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                switch (item.getId()) {
                    case 3:
                        fragment = new giasu_fragment();
                        break;
                }
                if (fragment != null)
                    frameLayout.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });
//set nút back
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManHinhUser.this, MainActivity.class));
            }
        });

    }

}