package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_admin_hopdong;
import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_bando;
import com.example.quanlygiasu_md18202_duan1.Fragment2.giasu_fragment;
import com.example.quanlygiasu_md18202_duan1.Fragment2.quanlyUser_fragment;
import com.example.quanlygiasu_md18202_duan1.Fragment2.quanly_dangky;
import com.example.quanlygiasu_md18202_duan1.R;

public class AdminContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        //TextView txtTitle = findViewById(R.id.txtTitle);
        Fragment fragment = null;
        Bundle bundle = getIntent().getExtras();
        int flag = bundle.getInt("flag", -1);
        switch (flag){
            case 1:
                fragment = new giasu_fragment();
                break;
            case 2:
                fragment = new quanlyUser_fragment();
                break;
            case 3:
                fragment = new quanly_dangky();
                break;
            case 4:
                fragment = new fragment_admin_hopdong();
                break;
            case 5:
                fragment = new giasu_fragment();
                break;
            case 6:
                fragment = new fragment_bando();
                break;
        }
        if(fragment!=null){
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
    }
}