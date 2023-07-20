package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.Adapter.Giasucuaban;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GiaSuCuaBan extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gia_su_cua_ban);
        getDS();
    }

    public void getDS() {
        TextView txtNoThing = findViewById(R.id.txtNoThing);
        GetListFireBase getListFireBase = new GetListFireBase();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("teacher");
        getListFireBase.readDatabase(databaseReference, new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {
                ArrayList<Teacher_MD> list2 = new ArrayList<>();
                for (Teacher_MD teacher_md : list) {
                    if(!teacher_md.getStatus().equals("Hoạt động")){
                        list2.add(teacher_md);
                    }
                }
                if(list2.size()==0){
                    txtNoThing.setVisibility(View.VISIBLE);
                }
                Giasucuaban giasucuaban = new Giasucuaban(list2);
                recyclerView = findViewById(R.id.recycleView2);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GiaSuCuaBan.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(giasucuaban);
            }

            @Override
            public void onListReceived1(ArrayList<User> list) {


            }
        });

    }
}