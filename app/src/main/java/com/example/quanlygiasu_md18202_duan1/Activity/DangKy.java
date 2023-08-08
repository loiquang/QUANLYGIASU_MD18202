package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Models.users.User;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangKy extends AppCompatActivity {

    ImageView imgBack;
    TextInputLayout tilUsername, tilPassword, tilRePassword;
    Button btnDangKy;
    TextView txtBack;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        imgBack = findViewById(R.id.imgBack);
        tilUsername = findViewById(R.id.tilUserName);
        tilPassword = findViewById(R.id.tilPassWord);
        tilRePassword = findViewById(R.id.tilRePassWord);
        btnDangKy = findViewById(R.id.btnDangKy);
        txtBack = findViewById(R.id.txtBack);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, DangNhap.class));
                finish();
            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this, DangNhap.class));
                finish();
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseDatabase = FirebaseDatabase.getInstance();
                userRef = firebaseDatabase.getReference("user");

                String userName = tilUsername.getEditText().getText().toString();
                String passWord = tilPassword.getEditText().getText().toString();
                String rePassWord = tilRePassword.getEditText().getText().toString();
                if (!validateUsername() | !validatePassword()) {
                    return;
                }
                if (!passWord.equals(rePassWord)) {
                    Toast.makeText(DangKy.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(userName)) {
                                tilUsername.setError("Tên tài khoản đã tồn tại");
                            } else {
                                tilUsername.setError(null);
                                User user = new User(null, null, passWord, null, 0, null);
                                userRef.child(userName).setValue(user);
                                Toast.makeText(DangKy.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

    public boolean validateUsername() {
        String val = tilUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            tilUsername.setError("Tên tài khoản không được để trống");
            tilUsername.setErrorIconDrawable(null);
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            tilUsername.setError("Tên tài khoản không được có khoảng trắng");
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
        String passWordVal = "^"
                + "(?=.*[0-9])"     // at least 1 digit
                + "(?=.*[a-zA-Z])"  // any letter
                + "(?=.*[@#$^&+=])"   // at least 1 special character
                + "(?=\\S+$)"       // no white spaces
                + ".{4,}"           // at least 4 characters
                + "$";
        if (val.isEmpty()) {
            tilPassword.setError("Mật khẩu không được để trống");
            tilRePassword.setError("Mật khẩu không được để trống");
            tilPassword.setErrorIconDrawable(null);
            tilRePassword.setErrorIconDrawable(null);
            return false;
        } else if (!val.matches(passWordVal)) {
            tilPassword.setError("Mật khẩu phải có một số, một ký tự đặc biệt và không chứa khoảng trắng");
            tilRePassword.setError("Mật khẩu phải có một số, một ký tự đặc biệt và không chứa khoảng trắng");
            tilPassword.setErrorIconDrawable(null);
            tilRePassword.setErrorIconDrawable(null);
            return false;
        } else {
            tilPassword.setError(null);
            tilRePassword.setError(null);
            return true;
        }
    }
}