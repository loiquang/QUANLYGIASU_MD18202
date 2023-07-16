package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.quanlygiasu_md18202_duan1.R;

public class SignatureActivity extends AppCompatActivity {
    private SignatureView signatureView;
    private ImageView signatureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signatureView = findViewById(R.id.signatureView);

        // Xử lý sự kiện khi người dùng nhấn nút Lưu chữ ký
        Button saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap signatureBitmap = signatureView.getSignatureBitmap();
                showSignatureResult(signatureBitmap);
            }
        });
        //Xử lý sự kiện ấn nút xóa
        Button deleteButton = findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignatureView signatureView = findViewById(R.id.signatureView);
                signatureView.clearSignature();
            }
        });
    }
    private void showSignatureResult(Bitmap signatureBitmap) {
        setContentView(R.layout.activity_contract);
        signatureImageView = findViewById(R.id.signatureImageView);
        signatureImageView.setImageBitmap(signatureBitmap);
    }
}