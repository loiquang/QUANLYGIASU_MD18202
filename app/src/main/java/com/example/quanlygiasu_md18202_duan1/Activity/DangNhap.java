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
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Adapter.MonHoc_User;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.AdminActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.VerificationActivity;
import com.example.quanlygiasu_md18202_duan1.Fragment2.giasu_fragment;
import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {

    private TextInputLayout tilUsername, tilPassword;
    private TextView txtDangKy, txtQuenMK;
    private Button btnDangNhap;
    private CheckBox checkRMB;
    private ImageView imgFace, imgInsta, imgGoogle;

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

        }
        txtQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, QuenMK.class);
                startActivity(intent);
            }
        });
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
                    ArrayList<User> list = new ArrayList<>();
                    tilUsername.setError(null);
                    String passWordFromDB = snapshot.child(userName).child("password").getValue(String.class);
                    String name = snapshot.child(userName).child("cccd").child("name").getValue(String.class);
                    long money = snapshot.child(userName).child("money").getValue(Long.class);
                    if (passWord.equals(passWordFromDB)) {
                        tilPassword.setError(null);

                        // Lưu lại trạng thái checkbox rememberme checkRMB (true hoặc false) khi đăng nhập của người dùng vào file isRememberData
                        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isRemember", checkRMB.isChecked());
                        editor.putString("user", userName);
                        editor.putString("pass", passWord);
                        editor.putString("name", name);
                        editor.putLong("money", money);
                        editor.apply();

                        if (userName.equals("admin")) {
                            startActivity(new Intent(DangNhap.this, AdminActivity.class));
                        } else if(snapshot.child(userName).child("cccd").getValue()==null) {
                            startActivity(new Intent(DangNhap.this, VerificationActivity.class));
                        }else{
                            Toast.makeText(DangNhap.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangNhap.this, ManHinhUser.class));
                            finish();
                        }


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