package com.example.quanlygiasu_md18202_duan1.InterFace;

import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.Models.Teacher_Models.Teacher_MD;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;

import java.util.ArrayList;

public interface Interface_list {
    void onListReceived(ArrayList<Teacher_MD> list);
    void onListReceived1(ArrayList<User> list);
    void onListReceived2(ArrayList<ReQuestGS> list);

}
