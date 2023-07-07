package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.quanlygiasu_md18202_duan1.Adapter.TeacherAdapter;
import com.example.quanlygiasu_md18202_duan1.Adapter.UserAdapter;
import com.example.quanlygiasu_md18202_duan1.Models.teacher;
import com.example.quanlygiasu_md18202_duan1.R;

import java.util.ArrayList;


public class giasu_fragment extends Fragment {

    private ArrayList<teacher> list;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giasu_fragment, container, false);
        SearchView searchView = view.findViewById(R.id.searchView);
        list = new ArrayList<>();
        list.add(new teacher(R.drawable.inta, "Đỗ Quang Lơi", "Bốc Vác"));
        list.add(new teacher(R.drawable.faceb, "Đỗ Quang Lơi22", "Bốc Vác"));
        list.add(new teacher(R.drawable.gg, "Đỗ Quang Lơi33", "Bốc Vác"));
        list.add(new teacher(R.drawable.inta, "Đỗ Quang Lơi44", "Bốc Vác"));
        recyclerView = view.findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        userAdapter = new UserAdapter(list);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);
        return view;
    }
}