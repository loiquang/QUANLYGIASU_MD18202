package com.example.quanlygiasu_md18202_duan1.FireBaseHelper;

import androidx.annotation.NonNull;

import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GetListFireBase {
    ArrayList<Teacher_MD> list = new ArrayList<>();
    ArrayList<User> list1 = new ArrayList<>();
    ArrayList<ReQuestGS> list2 = new ArrayList<>();
    DatabaseReference databaseReference;

    public GetListFireBase() {
    }

    public ArrayList<Teacher_MD> readDatabase(DatabaseReference databaseReference, Interface_list interface_list) {

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list != null) {
                    list.clear();
                }
                for (DataSnapshot dtSnap : snapshot.getChildren()) {
                    String key = dtSnap.getKey();
                    Teacher_MD a = dtSnap.getValue(Teacher_MD.class);
                    list.add(new Teacher_MD(key, a));
                }
                interface_list.onListReceived(list);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list;
    }

    public ArrayList<User> readDatabase2(DatabaseReference databaseReference, Interface_list interface_list) {

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list1 != null) {
                    list1.clear();
                }
                for (DataSnapshot dtSnap : snapshot.getChildren()) {
                    String key = dtSnap.getKey();
                    User user = dtSnap.getValue(User.class);
                    list1.add(new User(key, user));
                }
                interface_list.onListReceived1(list1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list1;
    }

    public ArrayList<ReQuestGS> readDatabase3(DatabaseReference databaseReference, Interface_list interface_list) {

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (list2 != null) {
                    list2.clear();
                }
                for (DataSnapshot dtSnap : snapshot.getChildren()) {
                    String key = dtSnap.getKey();
                    ReQuestGS reQuestGS = dtSnap.getValue(ReQuestGS.class);
                    list2.add(new ReQuestGS(key, reQuestGS));
                }
                interface_list.onListReceived2(list2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list2;
    }


}