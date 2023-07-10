package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangNhap extends AppCompatActivity {

    TextInputLayout tilUsername, tilPassword;
    TextView txtDangKy, txtQuenMK;
    Button btnDangNhap;
    CheckBox checkRMB;
    ImageView imgFace, imgInsta, imgGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        tilUsername = findViewById(R.id.tilUsername);
        tilPassword = findViewById(R.id.tilPassword);
        txtDangKy = findViewById(R.id.txtDangKy);
        txtQuenMK = findViewById(R.id.txtQuenMK);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        checkRMB = findViewById(R.id.checkRMB);
        imgFace = findViewById(R.id.imgFace);
        imgInsta = findViewById(R.id.imgInsta);
        imgGoogle = findViewById(R.id.imgGoogle);

        // Check giá trị trong file isRememberData
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
        boolean isRemember = sharedPreferences.getBoolean("isRemember", false);

        if (isRemember) {
            String user = sharedPreferences.getString("user", "");
            String pass = sharedPreferences.getString("pass", "");
            tilUsername.getEditText().setText(user);
            tilPassword.getEditText().setText(pass);
            checkRMB.setChecked(isRemember);
//            startActivity(new Intent(DangNhap.this, ManHinhUser.class));
        }

        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()) {
                    return;
                }
                checkUser();
            }
        });
    }

    public boolean validateUsername() {
        String val = tilUsername.getEditText().getText().toString();
        if (val.isEmpty()) {
            tilUsername.setError("Tên tài khoản không được để trống");
            tilUsername.setErrorIconDrawable(null);
            return false;
        } else {
            tilUsername.setError(null);
            tilUsername.setErrorIconDrawable(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String val = tilPassword.getEditText().getText().toString();
        if (val.isEmpty()) {
            tilPassword.setError("Mật khẩu không được để trống");
            tilPassword.setErrorIconDrawable(null);
            return false;
        } else {
            tilPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userName = tilUsername.getEditText().getText().toString();
        String passWord = tilPassword.getEditText().getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference userRef = firebaseDatabase.getReference("user");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(userName)) {
                    tilUsername.setError(null);
                    String passWordFromDB = snapshot.child(userName).child("password").getValue(String.class);
                    if (passWord.equals(passWordFromDB)) {
                        tilPassword.setError(null);

                        // Lưu lại trạng thái checkbox rememberme checkRMB (true hoặc false) khi đăng nhập của người dùng vào file isRememberData
                        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isRemember", checkRMB.isChecked());
                        editor.putString("user", userName);
                        editor.putString("pass", passWord);
                        editor.apply();

                        startActivity(new Intent(DangNhap.this, ManHinhUser.class));

                    } else {
                        tilPassword.setError("Mật khẩu không đúng");
                        tilPassword.setErrorIconDrawable(null);
                    }
                } else {
                    tilUsername.setError("Tên tài khoản không tồn tại");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}