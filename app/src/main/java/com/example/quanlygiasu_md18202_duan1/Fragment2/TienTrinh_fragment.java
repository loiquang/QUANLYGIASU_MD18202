package com.example.quanlygiasu_md18202_duan1.Fragment2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Adapter.TienTrinh;
import com.example.quanlygiasu_md18202_duan1.FireBaseHelper.GetListFireBase;
import com.example.quanlygiasu_md18202_duan1.InterFace.Interface_list;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class TienTrinh_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tien_trinh_fragment, container, false);
        GetListFireBase getListFireBase = new GetListFireBase();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("request");
        getListFireBase.readDatabase3(databaseReference, new Interface_list() {
            @Override
            public void onListReceived(ArrayList<Teacher_MD> list) {

            }

            @Override
            public void onListReceived1(ArrayList<User> list) {

            }

            @Override
            public void onListReceived2(ArrayList<ReQuestGS> list) {
try {
    ArrayList<ReQuestGS> list2 = new ArrayList<>();
    SharedPreferences sharedPreferences = getContext().getSharedPreferences("isRememberData", MODE_PRIVATE);
    String name = sharedPreferences.getString("user", "");
    Toast.makeText(getContext(), ""+name, Toast.LENGTH_SHORT).show();
    for (ReQuestGS reQuestGS : list) {
        String id = reQuestGS.getId();
        String[] id1 = id.split("-");
        String id2 = id1[0];
        if(id2.equals(name)){
            list2.add(reQuestGS);
        }
    }
    RecyclerView recyclerView = view.findViewById(R.id.recycleViewTT);
    TienTrinh tienTrinh = new TienTrinh(list2, view.getContext());
    LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(view.getContext());
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(tienTrinh);
}catch(Exception e){}



            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}