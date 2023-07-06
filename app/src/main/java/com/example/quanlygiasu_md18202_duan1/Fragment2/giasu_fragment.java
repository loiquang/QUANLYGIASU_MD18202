package com.example.quanlygiasu_md18202_duan1.Fragment2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.quanlygiasu_md18202_duan1.R;


public class giasu_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giasu_fragment, container, false);
         SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        return view;
    }
}