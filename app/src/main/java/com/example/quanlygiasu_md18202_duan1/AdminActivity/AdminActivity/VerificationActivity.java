package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.API.eKYCAPIService;
import com.example.quanlygiasu_md18202_duan1.Models.users.CCCD;
import com.example.quanlygiasu_md18202_duan1.Models.users.Infor;
import com.example.quanlygiasu_md18202_duan1.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {

    ImageView ivCMNDtruoc, ivCMNDsau;
   ImageView btnCMNDtruoc, btnCMNDsau;
    Button btnUpload;
    Uri uriMatTruoc, uriMatSau;
    Infor infor;
    CCCD cccd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        initUI();
        infor = new Infor();
        cccd = new CCCD();

        // Load ảnh CCCD mặt trước
        btnCMNDtruoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút được nhấn nút CMND trước
                ImagePicker.Companion.with(VerificationActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(101);

            }
        });

        // Load ảnh CCCD mặt sau
        btnCMNDsau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivCMNDtruoc.getDrawable() == null) {
                    Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp CMND/CCCD mặt trước đầu tiên", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý sự kiện khi nút được nhấn nút CMND sau
                    ImagePicker.Companion.with(VerificationActivity.this)
                            .crop()
                            .compress(1024)
                            .maxResultSize(1080, 1080)
                            .start(102);
                }
            }
        });

        // Upload thông tin qua ResultVerificationActivity
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivCMNDtruoc.getDrawable() == null || ivCMNDsau.getDrawable() == null) {
                    Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp đầy đủ và chính xác CMND/CCCD cả hai mặt", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(VerificationActivity.this, ResultVerificationActivity.class);
                    startActivity(intent);
                    ivCMNDtruoc.setImageURI(null);
                    ivCMNDsau.setImageURI(null);
                }
            }
        });
    }

    private void initUI() {
        ivCMNDtruoc = findViewById(R.id.ivCMNDtruoc);
        ivCMNDsau = findViewById(R.id.ivCMNDsau);
        btnCMNDtruoc = findViewById(R.id.btnCMNDtruoc);
        btnCMNDsau = findViewById(R.id.btnCMNDsau);
        btnUpload = findViewById(R.id.btnUpload);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            uriMatTruoc = data.getData();
            ivCMNDtruoc.setImageURI(uriMatTruoc);
            callApiGetInforCCCDMatTruoc(uriMatTruoc);

        } else if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            uriMatSau = data.getData();
            ivCMNDsau.setImageURI(uriMatSau);
            callApiGetInforCCCDMatSau(uriMatSau);
        } else {
            Toast.makeText(this, "Không có ảnh nào được chọn", Toast.LENGTH_SHORT).show();
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String filePath = cursor.getString(columnIndex);
            cursor.close();
            return filePath;
        }
    }

    private void callApiGetInforCCCDMatTruoc(Uri imageUri) {
        // Tạo một File từ Uri
        File file = new File(getRealPathFromUri(imageUri));

        // Tạo RequestBody từ file ảnh
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);

        // Tạo MultipartBody.Part từ RequestBody
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        MultipartBody.Part imagePartAva = MultipartBody.Part.createFormData("face", String.valueOf(1));

        eKYCAPIService.ekycApiService.getInforCCCD(imagePart, imagePartAva).enqueue(new Callback<Infor>() {
            @Override
            public void onResponse(Call<Infor> call, Response<Infor> response) {
                ResponseBody inforError = response.errorBody();
                if (inforError == null) {
                    Infor inforMatTruoc = response.body();
                    String type = inforMatTruoc.getData().get(0).getType();
                    if (type.equals("old") || type.equals("new") || type.equals("chip_front")) {
                        cccd = inforMatTruoc.getData().get(0);
                    } else {
                        Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp ảnh CMND/CCCD mặt trước", Toast.LENGTH_SHORT).show();
                        ivCMNDtruoc.setImageURI(null);
                    }
                } else {
                    Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp ảnh CMND/CCCD mặt trước", Toast.LENGTH_SHORT).show();
                    ivCMNDtruoc.setImageURI(null);
                }

            }

            @Override
            public void onFailure(Call<Infor> call, Throwable t) {
                Log.e("DangTai", String.valueOf(t));
                Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp ảnh CMND/CCCD rõ nét", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void callApiGetInforCCCDMatSau(Uri imageUri) {
        // Tạo một File từ Uri
        File file = new File(getRealPathFromUri(imageUri));

        // Tạo RequestBody từ file ảnh
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);

        // Tạo MultipartBody.Part từ RequestBody
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        eKYCAPIService.ekycApiService.getInforCCCD(imagePart, null).enqueue(new Callback<Infor>() {
            @Override
            public void onResponse(Call<Infor> call, Response<Infor> response) {
                ResponseBody inforError = response.errorBody();
                if (inforError == null) {
                    Infor inforMatSau = response.body();
                    String type = inforMatSau.getData().get(0).getType();
                    if (cccd.getType().equals("old") && type.equals("old_back")) {
                        cccd.setIssue_date(inforMatSau.getData().get(0).getIssue_date());
                    } else if (cccd.getType().equals("new") && type.equals("new_back")) {
                        cccd.setIssue_date(inforMatSau.getData().get(0).getIssue_date());
                    } else if (cccd.getType().equals("chip_front") && type.equals("chip_back")) {
                        cccd.setIssue_date(inforMatSau.getData().get(0).getIssue_date());
                    } else {
                        Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp ảnh CMND/CCCD mặt sau", Toast.LENGTH_SHORT).show();
                        ivCMNDsau.setImageURI(null);
                    }
                } else {
                    Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp ảnh CMND/CCCD mặt sau", Toast.LENGTH_SHORT).show();
                    ivCMNDsau.setImageURI(null);
                }

            }

            @Override
            public void onFailure(Call<Infor> call, Throwable t) {
                Log.e("DangTai", String.valueOf(t));
                Toast.makeText(VerificationActivity.this, "Vui lòng cung cấp ảnh CMND/CCCD rõ nét", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Chưa thực hiện xong thao tác", Toast.LENGTH_SHORT).show();
    }
}