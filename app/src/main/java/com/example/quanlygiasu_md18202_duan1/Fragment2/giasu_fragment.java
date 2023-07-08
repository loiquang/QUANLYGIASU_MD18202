package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.quanlygiasu_md18202_duan1.Adapter.MonHoc_User;
import com.example.quanlygiasu_md18202_duan1.Models.MonHoc_User_Models;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;


public class giasu_fragment extends Fragment {

    private ArrayList<MonHoc_User_Models> list;
    private RecyclerView recyclerView;
    private MonHoc_User userAdapter;
private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giasu_fragment, container, false);
        searchView = view.findViewById(R.id.searchView);
        list = new ArrayList<>();
        list.add(new MonHoc_User_Models(R.drawable.inta, "Do Quang Loi", "Bốc Vác"));
        list.add(new MonHoc_User_Models(R.drawable.faceb, "Do Quang Loi22", "Bốc Vác"));
        list.add(new MonHoc_User_Models(R.drawable.gg, "Do Quang Loi33", "Bốc Vác"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Do Quang Loi44", "Bốc Vác"));
        list.add(new MonHoc_User_Models(R.drawable.inta, "Dang Trong Tai", "Bốc Vác"));
        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        userAdapter = new MonHoc_User(list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);
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