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
        String soHS = bundle.getString("scale");
        String id = sharedPreferences.getString("id", "");
        int tien = bundle.getInt("price");
        String subject = bundle.getString("subject");
        txtTenGV.setText(tenGV);
        txtSoHS.setText(soHS);
        NumberFormat numberFormat = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.US));
        txtTien.setText("" + numberFormat.format(tien) + " " + "vnđ/buổi");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HoSoGiaSu.this, ManHinhUser.class));
                finish();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(HoSoGiaSu.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        btnDangKyGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("request");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
                        String nameUser = sharedPreferences.getString("name", "");
                        String user = sharedPreferences.getString("user", "");
                        if (snapshot.hasChild(user +"-"+ id)) {
                            Toast.makeText(HoSoGiaSu.this, "Đã Đăng Ký", Toast.LENGTH_SHORT).show();
                            return;
                        }

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
                        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_dialog);
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
                                if (scale1 > Integer.parseInt(soHS) || scale1 == 0) {
                                    Toast.makeText(HoSoGiaSu.this, "Số Học Sinh Vượt Quá", Toast.LENGTH_SHORT).show();
                                    return;
                                } else if(Integer.parseInt(edtTextN.getText().toString())<10){
                                    Toast.makeText(HoSoGiaSu.this, "Số buổi quá ít", Toast.LENGTH_SHORT).show();
                                    return;
                                } else if (edtTextB.getText().toString().isEmpty() || edtTextN.getText().toString().isEmpty()) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(HoSoGiaSu.this);
                                    builder1.setTitle("Warning");
                                    builder1.setMessage("Không Được Để Trống Thông Tin");
                                    builder1.setIcon(R.drawable.baseline_warning_amber_24);
                                    builder1.setNegativeButton("OKE", null);
                                    builder1.show();
                                    return;
                                } else {
                                    long thanhTien = (Long.parseLong(edtTextN.getText().toString()) * tien) * Long.parseLong(edtSoHS.getText().toString());
                                    txtThanhTien.setText("" + thanhTien);
                                    Toast.makeText(HoSoGiaSu.this, "" + thanhTien, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        btnOke.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String startDate = edtTextB.getText().toString();
                                String endDate = edtTextN.getText().toString();
                                int scale1 = Integer.parseInt(edtSoHS.getText().toString());
                                if (scale1 > Integer.parseInt(soHS) || scale1 == 0) {
                                    Toast.makeText(HoSoGiaSu.this, "Số Học Sinh Vượt Quá", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else if(Integer.parseInt(endDate)<10){
                                    Toast.makeText(HoSoGiaSu.this, "Số buổi quá ít", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (startDate.isEmpty() || endDate.isEmpty()) {
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(HoSoGiaSu.this);
                                    builder1.setTitle("Warning");
                                    builder1.setMessage("Không Được Để Trống Thông Tin");
                                    builder1.setIcon(R.drawable.baseline_warning_amber_24);
                                    builder1.setNegativeButton("OKE", null);
                                    builder1.show();
                                    return;
                                } else {
                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference = firebaseDatabase.getReference("request");
                                        long thanhTien = (Long.parseLong(endDate) * tien) * Long.parseLong(edtSoHS.getText().toString());
                                        int status = 0;
                                        ReQuestGS reQuestGS = new ReQuestGS(endDate, scale1, startDate, status, subject, tenGV, Math.abs(thanhTien), nameUser);
                                        databaseReference.child(user +"-"+ id).setValue(reQuestGS);
                                        Toast.makeText(HoSoGiaSu.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();

                                }
                            }
                        });
                        btnHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

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

//    public boolean checkDate(String stringDay1, String stringDay2) {
//        int day1 = getDayFromDate(stringDay1);
//        int day2 = getDayFromDate(stringDay2);
//        int month1 = getMonthFromDate(stringDay1);
//        int month2 = getMonthFromDate(stringDay2);
//        int year1 = getYearFromDate(stringDay1);
//        int year2 = getYearFromDate(stringDay2);
//        LocalDate currentDate = LocalDate.now();
//        int year = currentDate.getYear();
//        int month = currentDate.getMonthValue();
//        int day = currentDate.getDayOfMonth();
//        if (month1 < month || month2 < month) {
//            return false;
//        } else if (year1 < year || year2 < year || year2 < year1) {
//            return false;
//        } else if (month2 < month1 && year2 < year1) {
//            return false;
//        } else if (day1 < day || day2 < day && month1 == month2) {
//            return false;
//        } else if (month1 > month2 && year1 == year2) {
//            return false;
//        }
//        return true;
//    }

//    public long tinhNgay(String stringDay1, String stringDay2) {
//        int day1 = getDayFromDate(stringDay1);
//        int day2 = getDayFromDate(stringDay2);
//        int month1 = getMonthFromDate(stringDay1);
//        int month2 = getMonthFromDate(stringDay2);
//        int year1 = getYearFromDate(stringDay1);
//        int year2 = getYearFromDate(stringDay2);
//        LocalDate startDate = LocalDate.of(year1, month1, day1);
//        // Ngày kết thúc
//        LocalDate endDate = LocalDate.of(year2, month2, day2);
//        // Tính khoảng cách giữa hai ngày trong nhiều tháng
//        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
//        return daysBetween;
//    }

//    public static int getDayFromDate(String dateString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate localDate = LocalDate.parse(dateString, formatter);
//        return localDate.getDayOfMonth();
//    }
//
//    public static int getMonthFromDate(String dateString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate localDate = LocalDate.parse(dateString, formatter);
//        return localDate.getMonthValue();
//    }
//
//    public static int getYearFromDate(String dateString) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate localDate = LocalDate.parse(dateString, formatter);
//        return localDate.getYear();
//    }

}