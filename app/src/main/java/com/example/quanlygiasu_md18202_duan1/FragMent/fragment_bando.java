package com.example.quanlygiasu_md18202_duan1.FragMent;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlygiasu_md18202_duan1.Adapter.LichSuGD;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class fragment_bando extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bando, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycleViewHis);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("request");
        GetListFireBase getListFireBase = new GetListFireBase();
        getListFireBase.readDatabase3(databaseReference, new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {

            }

            @Override
            public void onListReceived1(ArrayList<User> list) {

            }

            @Override
            public void onListReceived2(ArrayList<ReQuestGS> list) {
                ArrayList<ReQuestGS> list1 = new ArrayList<>();
                for (ReQuestGS reQuestGS: list) {
                    if(reQuestGS.getReQuestGS().getStatus()!=2)
                        list1.add(reQuestGS);
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setLayoutManager(linearLayoutManager);
                LichSuGD lichSuGD = new LichSuGD(list1, getContext());
                recyclerView.setAdapter(lichSuGD);

            }
        });

        return view;
    }
}
