package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlygiasu_md18202_duan1.Adapter.QuanLyUser;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class quanlyUser_fragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.combo_fragment, container, false);
GetDS(view);
        return view;
    }
    public void GetDS(View view){
        GetListFireBase getListFireBase = new GetListFireBase();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("user");
        getListFireBase.readDatabase2(databaseReference, new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {

            }

            @Override
            public void onListReceived1(ArrayList<User> list) {
                ArrayList<User> list1 = new ArrayList<>();
                for (User user: list) {
                    if(!user.getId().equals("admin"))
                        list1.add(user);

                }
                recyclerView = view.findViewById(R.id.recycleViewCombo);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                QuanLyUser user = new QuanLyUser(list1, getContext());
                recyclerView.setAdapter(user);
            }
            @Override
            public void onListReceived2(ArrayList<ReQuestGS> list) {

            }
        });

    }
}