package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoiMKActivity extends AppCompatActivity {
private TextInputLayout tilPass, tilRePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mkactivity);
        tilPass = findViewById(R.id.tilMKCu);
        tilRePass = findViewById(R.id.tilMKMoi);
//        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
//        String user = sharedPreferences.getString("user", "");
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference = firebaseDatabase.getReference().child("user").child(user).child("pass");
    }
    public boolean validatePassword() {
        String val = tilPass.getEditText().getText().toString();
        String passWordVal = "^"
                + "(?=.*[0-9])"     // at least 1 digit
                + "(?=.*[a-zA-Z])"  // any letter
                + "(?=.*[@#$^&+=])"   // at least 1 special character
                + "(?=\\S+$)"       // no white spaces
                + ".{4,}"           // at least 4 characters
                + "$";
        if (val.isEmpty()) {
            tilPass.setError("Mật khẩu không được để trống");
            tilRePass.setError("Mật khẩu không được để trống");
            tilPass.setErrorIconDrawable(null);
            tilRePass.setErrorIconDrawable(null);
            return false;
        } else if (!val.matches(passWordVal)) {
            tilPass.setError("Mật khẩu quá yếu hoặc có chứa khoảng trắng");
            tilRePass.setError("Mật khẩu quá yếu hoặc có chứa khoảng trắng");
            tilPass.setErrorIconDrawable(null);
            tilRePass.setErrorIconDrawable(null);
            return false;
        } else {
            tilPass.setError(null);
            tilRePass.setError(null);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}