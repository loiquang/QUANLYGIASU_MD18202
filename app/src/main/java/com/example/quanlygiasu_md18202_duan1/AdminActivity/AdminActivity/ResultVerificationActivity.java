package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quanlygiasu_md18202_duan1.Activity.DangNhap;
import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.Models.users.CCCD;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultVerificationActivity extends AppCompatActivity {

    TextView txtID, txtName, txtSex, txtDOB, txtAddress, txtIssueDate;
    ImageView ivAvatarUser;
    Button btnComplete, btnBack;
    CCCD cccd;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_verification);

        initUI();
        cccd = new CCCD();
        Intent intent = getIntent();
        if (intent != null) {
            cccd = (CCCD) intent.getSerializableExtra("cccd");
            if (cccd != null) {
                Glide.with(ResultVerificationActivity.this).load(cccd.getFace()).into(ivAvatarUser);
                txtID.setText(cccd.getId());
                txtName.setText(cccd.getName());
                txtSex.setText(cccd.getSex());
                txtDOB.setText(cccd.getDob());
                txtAddress.setText(cccd.getAddress());
                txtIssueDate.setText(cccd.getIssue_date());
            }
        }

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                userRef = firebaseDatabase.getReference("user");
                SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
                String user = sharedPreferences.getString("user", "");
                userRef.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.hasChild("cccd")){
                            userRef.child(user).child("cccd").setValue(cccd);
                            Toast.makeText(ResultVerificationActivity.this, "Xác thực CMND/CCCD thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResultVerificationActivity.this, DangNhap.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUI() {
        ivAvatarUser = findViewById(R.id.ivAvatarUser);
        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        txtSex = findViewById(R.id.txtSex);
        txtDOB = findViewById(R.id.txtDOB);
        txtAddress = findViewById(R.id.txtAddress);
        txtIssueDate = findViewById(R.id.txtIssueDate);
        btnComplete = findViewById(R.id.btnComplete);
        btnBack = findViewById(R.id.btnBack);
    }
}