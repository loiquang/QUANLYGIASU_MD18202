package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.MoMoController.MoMoConfig;
import com.example.quanlygiasu_md18202_duan1.MoMoController.MoMoPayment;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    TextView tvAmount, tvDiscount, tvTotalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initUI();

        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
        user = sharedPreferences.getString("user", "");

        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference("user");

        btnWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    }

    //Get token through MoMo app
    private void requestPayment() {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
        if (tvTotalAmount.getText().toString() != null && tvTotalAmount.getText().toString().trim().length() != 0)
            amount = Integer.parseInt(tvTotalAmount.getText().toString().trim());

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
                    String partnerRefId = UUID.randomUUID().toString();
                    if (data.getStringExtra("data") != null && !data.getStringExtra("data").equals("")) {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                        MoMoPayment.payment(PaymentActivity.this, Long.parseLong(String.valueOf(amount)), partnerRefId, phoneNumber, token, new MoMoPayment.PaymentCallback() {
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
}