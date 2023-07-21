package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.R;

public class HopDongActivity extends AppCompatActivity {
    private ImageView signatureImageView;
    TextView txtSignture;
    Button btnContinues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong);

        txtSignture = findViewById(R.id.txtSignture);
        RadioButton rdoAccept = findViewById(R.id.rdoAccept);
        btnContinues = findViewById(R.id.btnContinues);

        btnContinues.setVisibility(View.GONE);

        txtSignture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rdoAccept.isChecked()){
                    showSignatureDialog();
                }else{
                    Toast.makeText(HopDongActivity.this, "Bạn phải đồng ý các điều khoản ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showSignatureDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_signture, null);
        dialogBuilder.setView(dialogView);

        final SignatureView signatureView = dialogView.findViewById(R.id.signatureView1);

        dialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Lấy dữ liệu chữ ký từ SignatureView
                Bitmap signatureBitmap = signatureView.getSignatureBitmap();
                showSignatureResult(signatureBitmap);
                // Ẩn txtSignture sau khi nhấn nút Đồng ý
                txtSignture.setVisibility(View.GONE);
                btnContinues.setVisibility(View.VISIBLE);
            }
        });

        dialogBuilder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Xử lý khi người dùng nhấn nút xóa hoặc có thể bỏ qua nếu không cần xử lý gì
                SignatureView signatureView = dialogView.findViewById(R.id.signatureView1);
                signatureView.clearSignature();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void showSignatureResult(Bitmap signatureBitmap) {
        signatureImageView = findViewById(R.id.signatureImageView1);
        signatureImageView.setImageBitmap(signatureBitmap);
    }

}