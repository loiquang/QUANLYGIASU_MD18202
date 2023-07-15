package com.example.quanlygiasu_md18202_duan1.FireBaseHelper;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.quanlygiasu_md18202_duan1.Adapter.Teacher_In;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetListFireBase {
    ArrayList<Teacher_MD> list = new ArrayList<>();
    public GetListFireBase() {
    }

    public ArrayList<Teacher_MD> readDatabase( Interface_list interface_list) {
        FirebaseDatabase auth = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = auth.getReference("teacher");
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list!=null){
                    list.clear();
                }
                for (DataSnapshot dtSnap : snapshot.getChildren()) {

                    Teacher_MD a = dtSnap.getValue(Teacher_MD.class) ;
                    list.add(a);
                }
                interface_list.onListReceived(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list;
    }

}