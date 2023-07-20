package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.Adapter.MonHoc_User;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.ManagerCoachActivity;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class giasu_fragment extends Fragment {

    private ArrayList<MonHoc_User_Models> list;
    private RecyclerView recyclerView;
    private MonHoc_User userAdapter;
    private SearchView searchView;
    private TextView txtTimKiem;
    private int flag = 1;
    private GetListFireBase getListFireBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giasu_fragment, container, false);
        searchView = view.findViewById(R.id.searchView);
        txtTimKiem = view.findViewById(R.id.txtTimKiem);

        getList(view);
        getName(view);
        //click vào nút search
//   int flag1 =1;
//        if(flag1==1){
//            txtXinChao.setVisibility(View.INVISIBLE);
//            txtName.setText("Quản Lý Gia Sư");
//        }
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 1) {
                    txtTimKiem.setVisibility(View.GONE);
                    ViewGroup.LayoutParams params = searchView.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    searchView.setLayoutParams(params);
                    flag++;
                }
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                if (flag == 2) {
                    txtTimKiem.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams params = searchView.getLayoutParams();
                    params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                    searchView.setLayoutParams(params);
                    flag--;
                }
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return view;
    }

    public void getList(View view) {
        list = new ArrayList<>();
        getListFireBase = new GetListFireBase();
        list.add(new MonHoc_User_Models(R.drawable.math, "Toán học"));
        list.add(new MonHoc_User_Models(R.drawable.book, "Ngữ văn"));
        list.add(new MonHoc_User_Models(R.drawable.translator, "Tiếng Anh"));
        list.add(new MonHoc_User_Models(R.drawable.chemistry, "Hóa học"));
        list.add(new MonHoc_User_Models(R.drawable.atomic, "Vật lý"));
        list.add(new MonHoc_User_Models(R.drawable.biology, "Sinh học"));
        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        userAdapter = new MonHoc_User(list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);
    }

    public void getName(View view) {
        TextView txtXinChao = view.findViewById(R.id.txtXinchao);
        TextView txtName = view.findViewById(R.id.txtName);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("isRememberData", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", "");
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user").child(user).child("cccd").child("fullname");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    String a = snapshot.getValue().toString();
                    txtName.setText(a);
                }catch (Exception e){
                    txtName.setText("Quản Lý Gia Sư");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}