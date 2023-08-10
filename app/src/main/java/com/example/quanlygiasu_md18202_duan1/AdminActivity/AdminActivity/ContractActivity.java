package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import static android.graphics.BitmapFactory.decodeByteArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.Contract;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class ContractActivity extends AppCompatActivity {
    private TextView txtDate, txtID, txtUser, txtBuoi, txtTotal, txtPayment, txtToday;
    private Button btnOke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        txtDate = findViewById(R.id.txtDate);
        txtID = findViewById(R.id.txtID);
        txtUser = findViewById(R.id.txtUser);
        txtBuoi = findViewById(R.id.txtBuoi);
        txtTotal = findViewById(R.id.txtTotal);
        txtPayment = findViewById(R.id.txtPayment);
        txtToday = findViewById(R.id.txtToDay);
        btnOke = findViewById(R.id.btnOke);

        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
        // Truy xuất Base64 String từ SharedPreferences
        String storedImageBase64 = sharedPreferences.getString("imageBitmap", "");

// Chuyển Base64 String thành byte[]
        byte[] storedByteArray = Base64.decode(storedImageBase64, Base64.DEFAULT);
        ImageView signatureImageView = findViewById(R.id.signatureImageView);
        Bitmap receivedBitmap = decodeByteArray(storedByteArray, 0, storedByteArray.length);
        signatureImageView.setImageBitmap(receivedBitmap);
        Intent intent = getIntent();
        ReQuestGS reQuestGS = new ReQuestGS();
        reQuestGS = (ReQuestGS) intent.getSerializableExtra("request");

        Bundle bundle = intent.getExtras();
        String date = bundle.getString("date");
        int how = bundle.getInt("how");
        if (how == 1) {
            txtPayment.setText("Trả Ví");
        }
        txtDate.setText(reQuestGS.getStartdate());
        txtID.setText(reQuestGS.getId());
        txtUser.setText(reQuestGS.getUser());
        txtBuoi.setText(reQuestGS.getDate()+ " ngày");
        NumberFormat numberFormat = new DecimalFormat("#,###");
        txtTotal.setText(numberFormat.format(reQuestGS.getTotalpayment()));
        txtToday.setText(date);
btnOke.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ReQuestGS reQuestGS1 = new ReQuestGS();
        reQuestGS1 = (ReQuestGS) intent.getSerializableExtra("request");
        captureScreen(reQuestGS1.getId());
    startActivity(new Intent(ContractActivity.this, ManHinhUser.class));}
});
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Nhấn xác nhận để trở lại", Toast.LENGTH_SHORT).show();
    }
    private void captureScreen(String teacher) {

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        // Lấy WindowManager để truy cập màn hình
        WindowMetrics windowMetrics = windowManager.getCurrentWindowMetrics();
        Rect bounds = windowMetrics.getBounds();
//        int width = bounds.width();
//        int height = 1000;
        View viewToCapture = findViewById(R.id.viewCap);
        int x = 5; // Tọa độ x của phần cần chụp
        int y = 0; // Tọa độ y của phần cần chụp
        int width = viewToCapture.getWidth(); // Chiều rộng của phần cần chụp
        int height = viewToCapture.getHeight(); // Chiều cao của phần cần chụp

        Bitmap screenshot = capturePartOfView(viewToCapture, x, y, width, height);

        // Tạo bitmap để chứa ảnh màn hình
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // Lấy SurfaceView để chụp ảnh màn hình
//        View rootView = getWindow().getDecorView().getRootView();
//        rootView.draw(new Canvas(bitmap));

        // Lưu ảnh màn hình vào Storage
        saveScreenshotToStorage(screenshot, teacher);
    }

    private Bitmap capturePartOfView(View viewToCapture, int x, int y, int width, int height) {
        Bitmap screenshot = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenshot);
        canvas.translate(x, y);
        viewToCapture.draw(canvas);
        return screenshot;
    }

    private void saveScreenshotToStorage(Bitmap bitmap, String teacher) {
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
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
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
                        saveImageUrlToFirebaseDatabase(imageUrl, teacher);
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FirebaseStorage", "Lỗi tải ảnh lên: " + e.getMessage());

                        // Lỗi xảy ra trong quá trình tải ảnh lên Firebase Storage
                    }
                });
            }

            private void saveImageUrlToFirebaseDatabase(String imageUrl, String teacher) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("imageRequest");
                databaseReference.child(teacher).child("image").setValue(imageUrl);
            }
        });
    }
}