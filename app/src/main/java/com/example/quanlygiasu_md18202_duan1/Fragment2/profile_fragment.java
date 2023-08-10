package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quanlygiasu_md18202_duan1.Activity.GiaSuCuaBan;
import com.example.quanlygiasu_md18202_duan1.Activity.DoiMKActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.ThongTinActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.WalletUserActivity;
import com.example.quanlygiasu_md18202_duan1.R;


public class profile_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        Button btnGiaSu = view.findViewById(R.id.btnGiaSu);
        Button btnNapTien = view.findViewById(R.id.btnNapTien);
        Button btnThongTin = view.findViewById(R.id.btnThongTin);
        Button btnDoiMK = view.findViewById(R.id.btnDoiMK);
        btnGiaSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GiaSuCuaBan.class));
            }
        });
        btnNapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WalletUserActivity.class));
            }
        });
        btnThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ThongTinActivity.class));
            }
        });
        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DoiMKActivity.class));
            }
        });
        return view;
    }
}