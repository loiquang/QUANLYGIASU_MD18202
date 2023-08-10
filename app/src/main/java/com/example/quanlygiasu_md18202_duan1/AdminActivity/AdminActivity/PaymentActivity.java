package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Activity.GiaSuCuaBan;
import com.example.quanlygiasu_md18202_duan1.Activity.ManHinhUser;
import com.example.quanlygiasu_md18202_duan1.MoMoController.MoMoConfig;
import com.example.quanlygiasu_md18202_duan1.MoMoController.MoMoPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponseConfirmPayment;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import vn.momo.momo_partner.AppMoMoLib;

public class PaymentActivity extends AppCompatActivity {

    private long amount;
    int environment = 0;//developer default
    private long money;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;
    String user;
    LinearLayout btnWallet, btnMoMo;
    TextView tvAmount, tvDiscount, tvTotalAmount, txtSoDu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initUI();
        Bundle bundle = getIntent().getExtras();
        NumberFormat numberFormat = new DecimalFormat("#,###");
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
        long payment = bundle.getLong("paymentDone");
        money = sharedPreferences.getLong("money1", -1);
        long moneyAdmin = sharedPreferences.getLong("moneyAdmin", -1);
        double discount = 0;
        if (payment > 400000) {
            discount = 0.10;
        }
        if (payment <= 400000) {
            discount = 0.05;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.##%");
        tvAmount.setText(numberFormat.format(payment));
        tvDiscount.setText(decimalFormat.format(discount));
        double totalAmount = payment - (payment * discount);
        tvTotalAmount.setText(numberFormat.format(totalAmount));
        user = sharedPreferences.getString("user", "");
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference("user");
        userRef.child(user).child("money").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                money = snapshot.getValue(Long.class);
                txtSoDu.setText(numberFormat.format(money));
                SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("money1", money);
                editor.apply();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                if (money < payment) {
                    builder.setTitle("Thông Báo");
                    builder.setMessage("Tài Khoản Không Đủ Tiền!\n Nhấn Oke Để Nạp Tiền");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(PaymentActivity.this, WalletUserActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setPositiveButton("No", null);
                    builder.show();
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentActivity.this);
                    builder1.setTitle("Thông Báo").setMessage("Xác nhận thanh toán").setNegativeButton("Oke", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            double soDu = money - totalAmount;
                            DatabaseReference databaseReference = userRef.child(user).child("money");
                            databaseReference.setValue(soDu).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(PaymentActivity.this, "Thanh Toán Thành Công", Toast.LENGTH_SHORT).show();
                                    DatabaseReference databaseReference2 = userRef.child("admin").child("money");
                                    double soDuAdmin = moneyAdmin + totalAmount;
                                    databaseReference2.setValue(soDuAdmin);
                                    Done();
                                    startActivity(new Intent(PaymentActivity.this, ManHinhUser.class));
                                    finish();
                                }


                            });
                        }
                    }).show();

                }
            }
        });


        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT); // AppMoMoLib.ENVIRONMENT.PRODUCTION
        btnMoMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPayment();
            }
        });

    }

    private void initUI() {
        btnWallet = findViewById(R.id.btnWallet);
        btnMoMo = findViewById(R.id.btnMoMo);
        tvAmount = findViewById(R.id.tvAmount);
        tvDiscount = findViewById(R.id.tvDiscount);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        txtSoDu = findViewById(R.id.txtSoDu);
    }

    //Get token through MoMo app
    private void requestPayment() {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
        if (tvTotalAmount.getText().toString() != null && tvTotalAmount.getText().toString().trim().length() != 0)
            amount = Integer.parseInt(tvTotalAmount.getText().toString().replaceAll(",", ""));

        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("action", "gettoken"); // (required) action giá trị mặc định là "gettoken"
        eventValue.put("partner", "merchant"); // (required) partner giá trị mặc định là "merchant"
        eventValue.put("appScheme", MoMoConfig.APP_SCHEME); // (required) partnerSchemeId
        eventValue.put("amount", amount); // (required) Kiểu integer
        eventValue.put("description", MoMoConfig.DESCRIPTION); // (required) Mô tả
        eventValue.put("merchantcode", MoMoConfig.MERCHANT_CODE); // (required) Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("merchantname", MoMoConfig.MERCHANT_NAME); // (required) Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas

        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);

    }

    //Get token callback from MoMo app an submit to server side
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                if (data.getIntExtra("status", -1) == 0) {
                    // Dữ liệu MoMo trả về cho ứng dụng đối tác (MoMo Callback)
                    //tvMessage.setText("Token: " + "Get token " + data.getStringExtra("message"));
                    String token = data.getStringExtra("data"); //Token response
                    String phoneNumber = data.getStringExtra("phonenumber");
                    String partnerRefId = UUID.randomUUID().toString(); // Mã đơn hàng
                    String requestId = UUID.randomUUID().toString();
                    if (data.getStringExtra("data") != null && !data.getStringExtra("data").equals("")) {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                        MoMoPayment moMoPayment = new MoMoPayment();
                        moMoPayment.payment(PaymentActivity.this, Long.parseLong(String.valueOf(amount)), partnerRefId, phoneNumber, token, requestId, new MoMoPayment.PaymentCallback() {
                            @Override
                            public void onPaymentSuccess(ResponseConfirmPayment responseConfirmPayment) {
//                                DatabaseReference databaseReference2 = userRef.child("admin").child("money");
//                                double soDuAdmin = moneyAdmin+totalAmount;
//                                databaseReference2.setValue(soDuAdmin);
                                Done();
                            }

                            @Override
                            public void onPaymentSuccess(long amount) {
                                // TODO: Thực hiện thanh toán bằng MoMo ở đây


                            }

                            @Override
                            public void onPaymentFailure(String errorMessage) {

                            }
                        });


                    } else {
                        Toast.makeText(this, this.getString(R.string.not_receive_info), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //TOKEN FAIL
                    String message = data.getStringExtra("message") != null ? data.getStringExtra("message") : "Thất bại";
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
            } else {
                //tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                Toast.makeText(this, this.getString(R.string.not_receive_info), Toast.LENGTH_SHORT).show();
            }
        } else {
            // tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
            Toast.makeText(this, this.getString(R.string.not_receive_info_err), Toast.LENGTH_SHORT).show();
        }
    }

    public void Done() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("idPayment");
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child("request").child(id);
        databaseReference.child("status").setValue(3);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, GiaSuCuaBan.class));
        finish();
    }
}