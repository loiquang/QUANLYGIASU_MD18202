package com.example.quanlygiasu_md18202_duan1.FragMent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.textfield.TextInputLayout;


public class fragment_quenmk extends Fragment {
    private TextInputLayout tilQuenMK;
    private Button btnGuiMa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quenmk, container, false);
        tilQuenMK = view.findViewById(R.id.tilQuenMK);
        btnGuiMa = view.findViewById(R.id.btnGuiMa);
        btnGuiMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new fragment_nhapotp();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}