package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.FragMent.fragment_nhapotp;
import com.example.quanlygiasu_md18202_duan1.Fragment2.giasu_fragment;
import com.example.quanlygiasu_md18202_duan1.R;

import org.w3c.dom.Text;

public class ManagerCoachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_coach);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        TextView txtTitle = findViewById(R.id.txtTitle);
        Fragment fragment = null;
        Bundle bundle = getIntent().getExtras();
        int flag = bundle.getInt("flag", -1);
        switch (flag){
            case 1:
                fragment = new giasu_fragment();
                break;
            case 2:
                fragment = new fragment_nhapotp();


        }
        if(fragment!=null){
            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
        }
    }
}