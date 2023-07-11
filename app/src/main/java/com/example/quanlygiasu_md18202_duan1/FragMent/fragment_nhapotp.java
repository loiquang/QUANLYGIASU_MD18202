package com.example.quanlygiasu_md18202_duan1.FragMent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.Activity.DangNhap;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.internal.TextWatcherAdapter;

public class fragment_nhapotp extends Fragment {
    private EditText edt1, edt2, edt3, edt4, edt5, edt6;
    private TextView txtBack;
    private Button btnGuiMa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhapotp, container, false);
        edt1 = view.findViewById(R.id.edtNum1);
        edt2 = view.findViewById(R.id.edtNum2);
        edt3 = view.findViewById(R.id.edtNum3);
        edt4 = view.findViewById(R.id.edtNum4);
        edt5 = view.findViewById(R.id.edtNum5);
        edt6 = view.findViewById(R.id.edtNum6);
        txtBack = view.findViewById(R.id.txtBack);
        btnGuiMa = view.findViewById(R.id.btnGuiMa);
        //nhập số tiến tới
        nextEDT(edt1, edt2);
        nextEDT(edt2, edt3);
        nextEDT(edt3, edt4);
        nextEDT(edt4, edt5);
        nextEDT(edt5, edt6);
        //xóa số lùi lại
        preview(edt6, edt5);
        preview(edt5, edt4);
        preview(edt4, edt3);
        preview(edt3, edt2);
        preview(edt2, edt1);
        //trở lại màn hình chính
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DangNhap.class));
                getActivity().finish();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void nextEDT(EditText editText, EditText editText2) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {
                    editText2.requestFocus(); // Chuyển focus sang editText2
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void preview(EditText editText, EditText editText2) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    editText2.requestFocus(); // Chuyển focus sang editText2
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}