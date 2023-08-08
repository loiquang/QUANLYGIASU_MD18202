package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quanlygiasu_md18202_duan1.Activity.DangNhap;
import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.Models.users.CCCD;
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
                userRef = firebaseDatabase.getReference().child("user");
                Bundle bundle = getIntent().getExtras();
                String user = bundle.getString("userNew");
                captureScreen(ivAvatarUser, user);
                userRef.child(user).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!snapshot.child(user).hasChild("cccd")){
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
    private void captureScreen(ImageView imageView,String key) {
        // Lấy WindowManager để truy cập màn hình
        Drawable drawable = imageView.getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        // Lưu ảnh màn hình vào Storage
        saveScreenshotToStorage(bitmap, key);
    }

    private void saveScreenshotToStorage(Bitmap bitmap, String key) {
        // Khởi tạo Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Tham chiếu đến thư mục (hoặc đường dẫn) bạn muốn lưu ảnh
        StorageReference storageRef = storage.getReference().child("image");
        // Tạo một tên tệp duy nhất cho ảnh (ví dụ: "image_123.jpg")
        String fileName = "image_" + System.currentTimeMillis() + ".jpg";
        // Tạo tham chiếu đến tệp trong Firebase Storage
        StorageReference imageRef = storageRef.child(fileName);


        // Chuyển đổi Bitmap thành dữ liệu nhị phân
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageData = baos.toByteArray();

        // Lưu dữ liệu nhị phân (ảnh) lên Firebase Storage
        UploadTask uploadTask = imageRef.putBytes(imageData);


        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Thành công: ảnh đã được lưu lên Firebase Storage
                // Lấy URL truy cập để truy xuất ảnh từ Firebase Storage

                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Đây là URL để truy xuất ảnh từ Firebase Storage
                        String imageUrl = uri.toString();
                        // Lưu URL vào Firebase Realtime Database
                        saveImageUrlToFirebaseDatabase(imageUrl, key);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FirebaseStorage", "Lỗi tải ảnh lên: " + e.getMessage());

                        // Lỗi xảy ra trong quá trình tải ảnh lên Firebase Storage
                    }
                });
            }

            private void saveImageUrlToFirebaseDatabase(String imageUrl, String key) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("user").child(key);
                databaseReference.child("cccd").child("face").setValue(imageUrl);
            }
        });
    }
}