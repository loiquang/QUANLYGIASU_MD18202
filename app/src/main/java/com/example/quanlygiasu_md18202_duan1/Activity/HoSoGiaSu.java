package com.example.quanlygiasu_md18202_duan1.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.number.IntegerWidth;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.ThongTinActivity;
import com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity.VerificationActivity;
import com.example.quanlygiasu_md18202_duan1.Models.Request.ReQuestGS;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HoSoGiaSu extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private TextView txtTenGV;
    private ImageView imgBack;
    private TextView txtTien, txtSoHS, txtChungChi;

    private ImageView imgGV;
    private Button btnDangKyGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_so_gia_su);
        toolbar = findViewById(R.id.toolBar);
        txtTenGV = findViewById(R.id.txtTenGV);
        imgBack = findViewById(R.id.imgBack);
        txtTien = findViewById(R.id.txtTien);
        txtSoHS = findViewById(R.id.txtSoHS);
        txtChungChi = findViewById(R.id.txtChungChi);
        imgGV = findViewById(R.id.imgGV);
        btnDangKyGV = findViewById(R.id.btnDangKyGV);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", Context.MODE_PRIVATE);
        Bundle bundle = getIntent().getExtras();
        String tenGV = bundle.getString("name");
        String id = bundle.getString("id");
        String soHS = bundle.getString("scale");
        int tien = bundle.getInt("price");
        String image = bundle.getString("image");
        String subject = bundle.getString("subject");
        String phoneTC = bundle.getString("sdt");
        String emailTC = bundle.getString("email");
        String user = sharedPreferences.getString("user", "");
        txtTenGV.setText(tenGV);
        txtSoHS.setText(soHS);
        Glide.with(this).load(image).into(imgGV);
        NumberFormat numberFormat = new DecimalFormat("#,###");
        txtTien.setText("" + numberFormat.format(tien) + " " + "vnđ/buổi");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoSoGiaSu.this, ManHinhUser.class));
                finish();
            }
        });
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("request");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(user + "-" + id)) {
                    btnDangKyGV.setVisibility(View.GONE);
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnDangKyGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
                String nameUser = sharedPreferences.getString("name", "");
                String user = sharedPreferences.getString("user", "");
                AlertDialog.Builder builder = new AlertDialog.Builder(HoSoGiaSu.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.dialog_request, null);
                builder.setView(view);
                TextView txtTenGV = view.findViewById(R.id.txtNameTeach);
                TextView edtTextB = view.findViewById(R.id.edtTextB);
                EditText edtTextN = view.findViewById(R.id.edtTextN);
                EditText edtSoHS = view.findViewById(R.id.edtSoHS);
                TextView txtThanhTien = view.findViewById(R.id.txtTien);
                TextView txtToiDa = view.findViewById(R.id.txtToiDa);
                Button btnOke = view.findViewById(R.id.btnDK);
                Button btnHuy = view.findViewById(R.id.btnHuy);
                Button btnTamTinh = view.findViewById(R.id.btnTamTinh);
                txtTenGV.setText(tenGV);
                edtSoHS.setText("" + 1);
                txtToiDa.setText("Tối Đa: " + soHS);
                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_dialog);
                alertDialog.show();
                edtTextB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog(edtTextB);
                    }
                });

                btnTamTinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int scale1 = Integer.parseInt(edtSoHS.getText().toString());
                        String date = edtTextB.getText().toString();
                        if(!tinhNgay(date)){
                            Toast.makeText(HoSoGiaSu.this, "Sai định dạng ngày", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (scale1 > Integer.parseInt(soHS) || scale1 == 0) {
                            Toast.makeText(HoSoGiaSu.this, "Số Học Sinh Vượt Quá", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (edtTextB.getText().toString().isEmpty() || edtTextN.getText().toString().isEmpty()) {
                            Toast.makeText(HoSoGiaSu.this, "Không để trống thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            if (Integer.parseInt(edtTextN.getText().toString()) < 10) {
                                Toast.makeText(HoSoGiaSu.this, "Số buổi quá ít", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            long thanhTien = (Long.parseLong(edtTextN.getText().toString()) * tien) * Long.parseLong(edtSoHS.getText().toString());
                            txtThanhTien.setText(numberFormat.format(thanhTien) + " vnd");
                        }
                    }
                });
                btnOke.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String date = edtTextB.getText().toString();
                        String startDate = edtTextB.getText().toString();
                        String endDate = edtTextN.getText().toString();
                        int scale1 = Integer.parseInt(edtSoHS.getText().toString());

                        if(!tinhNgay(date)){
                            Toast.makeText(HoSoGiaSu.this, "Sai định dạng ngày", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (scale1 > Integer.parseInt(soHS) || scale1 == 0) {
                            Toast.makeText(HoSoGiaSu.this, "Số Học Sinh Vượt Quá", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (endDate.isEmpty()) {
                            Toast.makeText(HoSoGiaSu.this, "Không để trống thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            if (Integer.parseInt(endDate) < 10) {
                                Toast.makeText(HoSoGiaSu.this, "Số buổi quá ít", Toast.LENGTH_SHORT).show();
                                return;
                            }else  if(Integer.parseInt(endDate)>60){
                                Toast.makeText(HoSoGiaSu.this, "Tối đa 60 buổi", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference("request");
                            long thanhTien = (Long.parseLong(endDate) * tien) * Long.parseLong(edtSoHS.getText().toString());
                            ReQuestGS reQuestGS = new ReQuestGS(endDate, emailTC, image, phoneTC, scale1, startDate, 0, subject, tenGV, Math.abs(thanhTien), nameUser);
                            databaseReference.child(user + "-" + id).setValue(reQuestGS);
                            alertDialog.dismiss();
                            startActivity(new Intent(HoSoGiaSu.this, ManHinhUser.class));
                            finish();
                        }
                    }
                });
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


            }
        });
    }


    private void showDatePickerDialog(TextView editTextDate) {
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Hiển thị DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Xử lý ngày được chọn từ DatePickerDialog
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(Calendar.YEAR, year);
                selectedCalendar.set(Calendar.MONTH, month);
                selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Định dạng ngày và hiển thị trong EditText
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(selectedCalendar.getTime());
                editTextDate.setText(formattedDate);

            }
        }, year, month, day);

        datePickerDialog.show();

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ManHinhUser.class));
        finish();
    }


    public boolean tinhNgay(String stringDay1) {
        int day1 = getDayFromDate(stringDay1);
        int month1 = getMonthFromDate(stringDay1);
        int year1 = getYearFromDate(stringDay1);
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1; // Tháng trong Calendar bắt đầu từ 0
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        if (day1 < currentDay && month1 == currentMonth) {
            return false;
        } else if (day1>currentDay&&month1 < currentMonth||day1<currentDay&&month1<currentMonth) {
            return false;
        } else if (year1 < currentYear) {
            return false;
        }
        return true;
    }

    public static int getDayFromDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate.getDayOfMonth();
    }

    public static int getMonthFromDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate.getMonthValue();
    }

    public static int getYearFromDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate.getYear();
    }

}