package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.Adapter.MonHoc_User;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.R;

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
        list = new ArrayList<>();
        getListFireBase = new GetListFireBase();
        list.add(new MonHoc_User_Models(R.drawable.inta, "Toán học"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Ngữ văn"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Tiếng Anh"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Hóa học"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Vật lý"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Sinh học"));
        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        userAdapter = new MonHoc_User(list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);



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
                if(flag==2){
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

}