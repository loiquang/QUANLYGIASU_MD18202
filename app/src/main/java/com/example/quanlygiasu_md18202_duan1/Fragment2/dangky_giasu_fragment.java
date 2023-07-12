package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.R;


public class dangky_giasu_fragment extends Fragment {
    private TextView  txtTien, txtSoHS, txtChungChi;

    private ImageView imgGV;
    private Button btnDangKyGV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky_giasu_fragment, container, false);
        txtTien = view.findViewById(R.id.txtTien);
        txtSoHS = view.findViewById(R.id.txtSoHS);
        txtChungChi = view.findViewById(R.id.txtChungChi);
        imgGV = view.findViewById(R.id.imgGV);
        btnDangKyGV = view.findViewById(R.id.btnDangKyGV);
        // Inflate the layout for this fragment
        return view;
    }
}