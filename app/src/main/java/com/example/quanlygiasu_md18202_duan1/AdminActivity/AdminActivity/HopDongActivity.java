package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HopDongActivity extends AppCompatActivity {
    private ImageView signatureImageView;
    private TextView txtSignture, txtUser, txtMoney, txtTime, txtAddres, txtCancle;
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
        txtCancle = findViewById(R.id.txtHuy);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("idHopDong");
        String nameUser = bundle.getString("nameUser");
        String teacher = bundle.getString("nameTeacher");
        String startDate = bundle.getString("startDate");
        String endDate = bundle.getString("endDate");
        long payment = bundle.getLong("payment");
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
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
        txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HopDongActivity.this);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Bạn sẽ bị phạt 5% hợp đồng!\n\nBạn thực sự muốn hủy hợp đồng chứ");
                builder.setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String user = sharedPreferences.getString("user", "");
                        long money = sharedPreferences.getLong("money", -1);
                        long moneyAdmin = sharedPreferences.getLong("moneyAdmin", -1);
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference().child("request");
                        double phat = payment * 0.05;
                        double pushmoney = money - phat;
                        Toast.makeText(HopDongActivity.this, "" + phat, Toast.LENGTH_SHORT).show();
                        DatabaseReference databaseReference1 = firebaseDatabase.getReference().child("user").child(user).child("money");
                        DatabaseReference databaseReference2 = firebaseDatabase.getReference().child("teacher");
                        databaseReference1.setValue(pushmoney).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(HopDongActivity.this, "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
                                DatabaseReference databaseReference2 = firebaseDatabase.getReference().child("user").child("admin").child("money");
                                double soDuAdmin = moneyAdmin + phat;
                                databaseReference2.setValue(soDuAdmin);
                            }


                        });
                        databaseReference.child(id).child("status").setValue(4);
                        databaseReference.child(id).child("totalpayment").setValue(phat);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String[] parts = id.split("-");
                                String key1= parts[1];
                                databaseReference2.child(key1).child("status").setValue("Hoạt động");
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        Toast.makeText(HopDongActivity.this, "Đã hủy hợp đồng", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HopDongActivity.this, ManHinhUser.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setPositiveButton("No", null);
                builder.show();
            }
        });

        btnContinues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HopDongActivity.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Ký Hợp Đồng Sẽ Không Hoàn Tác!\nBạn đồng ý chứ");
                builder.setNegativeButton("Oke", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(HopDongActivity.this, PaymentActivity.class);
                        Bundle bundle1 = new Bundle();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        signatureImageView = findViewById(R.id.signatureImageView1);
                        Bitmap bitmap = ((BitmapDrawable) signatureImageView.getDrawable()).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream); // Nén ảnh với chất lượng 80%
                        byte[] byteArray = stream.toByteArray();
                        String base64Image = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        editor.putString("imageBitmap", base64Image);
                        editor.apply();

                        ReQuestGS reQuestGS = new ReQuestGS(endDate,startDate,teacher, nameUser, id, payment);
                        intent.putExtra("request", reQuestGS);
                        bundle1.putLong("paymentDone", payment);
                        bundle1.putString("idPayment", id);
                        intent.putExtras(bundle1);
                        startActivity(intent);
                        Done(id);
                        finish();

                    }
                });
                builder.setPositiveButton("No", null);
                builder.show();

            }
        });
        //lay thong tin hop dong

        NumberFormat numberFormat = new DecimalFormat("#,###");
        txtUser.setText(nameUser);
        txtAddres.setText(teacher);
        txtTime.setText("Thời gian thuê gia sư được bắt đầu từ ngày " + startDate + " trong vòng " + endDate + " hoặc khi hoàn thành các yêu cầu và mục tiêu đã thỏa thuận hoặc cho đến khi bên thuê yêu cầu chấm dứt dịch vụ gia sư này theo quy định tại mục 5 của hợp đồng này.");
        txtMoney.setText("Bên thuê cam kết trả cho bên cho thuê số tiền được thỏa thuận là " + numberFormat.format(payment) + "VND cho hợp đồng dịch vụ gia sư. Phương thức thanh toán và lịch trình thanh toán sẽ được thỏa thuận trong tài liệu phụ.");


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



    public void Done(String id) {
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("request").child(id);
        databaseReference.child("status").setValue(2);
    }
}