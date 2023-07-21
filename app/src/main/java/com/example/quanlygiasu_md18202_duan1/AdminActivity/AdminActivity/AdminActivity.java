package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ImageButton btnTeacher = findViewById(R.id.btnManagerCoach);
        ImageButton btnUser = findViewById(R.id.btnManagerUser);
        ImageButton btnDocument = findViewById(R.id.btnDocument);
        ImageButton btnStatistics = findViewById(R.id.btnStatistics);
        ImageButton btnRecommendation = findViewById(R.id.btnRecommendation);
        ImageButton btnMap = findViewById(R.id.btnMap);
        TextView txtWarning = findViewById(R.id.txtWarning);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("request");
        GetListFireBase getListFireBase = new GetListFireBase();
        getListFireBase.readDatabase3(databaseReference, new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {
            }

            @Override
            public void onListReceived1(ArrayList<User> list) {
            }

            @Override
            public void onListReceived2(ArrayList<ReQuestGS> list) {
                ArrayList<ReQuestGS> list2 = new ArrayList<>();
                for (ReQuestGS reQuestGS: list) {
                    if(reQuestGS.getStatus()==0){
                        list2.add(reQuestGS);
                    }
                }
                txtWarning.setText(""+list2.size());
            }
        });

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
        btnDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 3;
                connect(flag);
            }
        });
        btnStatistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 4;
                connect(flag);
            }
        });
        btnRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 5;
                connect(flag);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag = 6;
                connect(flag);
            }
        });


    }

    public void connect(int flag) {
        Intent intent = new Intent(AdminActivity.this, AdminContainerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("flag", flag);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}