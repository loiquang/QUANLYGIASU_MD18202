package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.R;

import javax.security.auth.Subject;

public class PhanHoiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);

        LinearLayout linearLayoutUser = findViewById(R.id.linearUser);
        LinearLayout linearLayoutAdmin = findViewById(R.id.linearAdmin);
        LinearLayout linearLayoutEdit = findViewById(R.id.linearEdit);
        TextView txtUser = findViewById(R.id.txtTextUser);
        TextView txtAdim = findViewById(R.id.txtTextAdmin);
        TextView txtNameTeacher = findViewById(R.id.nameTeacher);
        TextView txtSubject = findViewById(R.id.txtSubject);
        EditText edtUser = findViewById(R.id.edtUser);
        ImageView btnSend = findViewById(R.id.btnSend);
        linearLayoutUser.setVisibility(View.GONE);
        linearLayoutAdmin.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nameTeacher");
        String subject = bundle.getString("subject");
        txtNameTeacher.setText(name);
        txtSubject.setText(subject);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUser.getText() != null) {
                    String text = edtUser.getText().toString();
                    txtUser.setText(text);
                    linearLayoutUser.setVisibility(View.VISIBLE);
                    linearLayoutEdit.setVisibility(View.GONE);
                } else {
                    Toast.makeText(PhanHoiActivity.this, "Bạn phải nhập nội dung phản hồi", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}