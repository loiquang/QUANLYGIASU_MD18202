package com.example.quanlygiasu_md18202_duan1.FragMent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlygiasu_md18202_duan1.Adapter.Giasucuaban;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class fragment_admin_hopdong extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);
        recyclerView = view.findViewById(R.id.recycleViewRQAM);
        getDS(recyclerView);
        return view;
    }

    public void getDS(RecyclerView recyclerView) {
        GetListFireBase getListFireBase = new GetListFireBase();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("request");
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
                list2 = list;
                Giasucuaban giasucuaban = new Giasucuaban(list2, getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(giasucuaban);
            }


        });
}}