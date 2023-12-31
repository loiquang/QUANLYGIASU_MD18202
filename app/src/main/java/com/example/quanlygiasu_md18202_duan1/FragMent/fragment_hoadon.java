package com.example.quanlygiasu_md18202_duan1.FragMent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.quanlygiasu_md18202_duan1.Adapter.HoaDonRC;
import com.example.quanlygiasu_md18202_duan1.Models.Request.HoaDon;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_hoadon extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hoadon, container, false);
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("imageRequest");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<HoaDon> list = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren()) {
                    HoaDon hoaDon = item.getValue(HoaDon.class);
                    list.add(hoaDon);
                }
                RecyclerView recyclerView = view.findViewById(R.id.recyCle);
               LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getContext());
               recyclerView.setLayoutManager(linearLayoutManager);
               HoaDonRC hoaDon = new HoaDonRC(list, getContext());
               recyclerView.setAdapter(hoaDon);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}