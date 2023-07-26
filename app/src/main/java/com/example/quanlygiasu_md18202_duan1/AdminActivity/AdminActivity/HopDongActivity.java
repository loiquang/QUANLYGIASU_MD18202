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

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class HopDongActivity extends AppCompatActivity {
    private ImageView signatureImageView;
    private TextView txtSignture, txtUser, txtMoney, txtTime, txtAddres;
    private Button btnContinues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hop_dong);
        txtUser = findViewById(R.id.txtUser);
        txtAddres = findViewById(R.id.txtAddres);
        txtTime = findViewById(R.id.txtTime);
        txtMoney = findViewById(R.id.txtMoney);
        txtSignture = findViewById(R.id.txtSignture);
        RadioButton rdoAccept = findViewById(R.id.rdoAccept);
        btnContinues = findViewById(R.id.btnContinues);
        btnContinues.setVisibility(View.GONE);
        btnContinues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //lay thong tin hop dong
        Bundle bundle = getIntent().getExtras();
        String nameUser = bundle.getString("nameUser");
        String teacher = bundle.getString("nameTeacher");
        String startDate = bundle.getString("startDate");
        String endDate = bundle.getString("endDate");
        long payment = bundle.getLong("payment");
        NumberFormat numberFormat = new DecimalFormat("#,###");
        txtUser.setText(nameUser);
        txtAddres.setText(teacher);
        txtTime.setText("Thời gian thuê gia sư được bắt đầu từ ngày " + startDate + " và kéo dài cho đến ngày " + endDate + " hoặc khi hoàn thành các yêu cầu và mục tiêu đã thỏa thuận hoặc cho đến khi bên thuê yêu cầu chấm dứt dịch vụ gia sư này theo quy định tại mục 5 của hợp đồng này.");
        txtMoney.setText("Bên thuê cam kết trả cho bên cho thuê số tiền được thỏa thuận là " + numberFormat.format(payment) + "VND cho hợp đồng dịch vụ gia sư. Phương thức thanh toán và lịch trình thanh toán sẽ được thỏa thuận trong tài liệu phụ.");

        txtSignture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdoAccept.isChecked()) {
                    showSignatureDialog();
                } else {
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

        signatureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSignatureDialog();
            }
        });
    }

}