package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quanlygiasu_md18202_duan1.Activity.GiaSuCuaBan;
import com.example.quanlygiasu_md18202_duan1.Adapter.Giasucuaban;
import com.example.quanlygiasu_md18202_duan1.R;


public class profile_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        Button btnGiaSu = view.findViewById(R.id.btnGiaSu);
        btnGiaSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GiaSuCuaBan.class));
            }
        });
        return view;
    }
}