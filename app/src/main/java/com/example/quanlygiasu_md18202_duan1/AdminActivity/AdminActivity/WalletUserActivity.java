package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.Adapter.TransactionAdapter;
import com.example.quanlygiasu_md18202_duan1.MoMoController.MoMoConfig;
import com.example.quanlygiasu_md18202_duan1.MoMoController.MoMoPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponseConfirmPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.Transaction;
import com.example.quanlygiasu_md18202_duan1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import vn.momo.momo_partner.AppMoMoLib;

public class WalletUserActivity extends AppCompatActivity {

    private long amount;
    int environment = 0;//developer default
    private long money;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;
    String user;
    TextView tvMoney;
    EditText edtAmount;
    LinearLayout btnMoMo;
    RecyclerView rvTransactions;
    ArrayList<Transaction> list;
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_user);

        // Binding View
        initUI();

        // Lấy user trong file isRememberData(tạo lúc đăng nhập) bằng SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("isRememberData", MODE_PRIVATE);
        user = sharedPreferences.getString("user", "");

        // Khởi tạo database Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference("user");

        // Load data rvTransactions
        rvTransactions.setLayoutManager(new LinearLayoutManager(this));
        TransactionAdapter transactionAdapter = new TransactionAdapter(this, list);
        rvTransactions.setAdapter(transactionAdapter);


        // Load data "money", "transaction"
        userRef.child(user).child("wallet").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                NumberFormat numberFormat = new DecimalFormat("#,###");
            
                money = snapshot.child("money").getValue(long.class);
                tvMoney.setText("Số dư: " + numberFormat.format(money));

                if (snapshot.hasChild("transaction")) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.child("transaction").getChildren()) {
                        Transaction transaction = dataSnapshot.getValue(Transaction.class);
                        list.add(transaction);
                        Collections.reverse(list);
                    }
                    transactionAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Khởi tạo môi trường MoMo
        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT); // AppMoMoLib.ENVIRONMENT.PRODUCTION
        btnMoMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestPayment();
            }
        });

    }

    private void initUI() {
        tvMoney = findViewById(R.id.tvMoney);
        edtAmount = findViewById(R.id.edtAmount);
        btnMoMo = findViewById(R.id.btnMoMo);
        rvTransactions = findViewById(R.id.rvTransactions);
        list = new ArrayList<>();

    }

    //Get token through MoMo app
    private void requestPayment() {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
        if (edtAmount.getText().toString() != null && edtAmount.getText().toString().trim().length() != 0) {
            amount = Integer.parseInt(edtAmount.getText().toString().trim());
            if (amount > 1000) {
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
            } else if (amount >= 50000000) {
                Toast.makeText(this, "MoMo: Số tiền cần nạp tối đa 50.000.000 VNĐ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "MoMo: Số tiền cần nạp tối thiểu 1.000 VNĐ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Hãy nhập số tiền cần nạp", Toast.LENGTH_SHORT).show();
        }


    }

    //Get token callback from MoMo app an submit to server side
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if (data != null) {
                if (data.getIntExtra("status", -1) == 0) {
                    // Dữ liệu MoMo trả về cho ứng dụng đối tác (MoMo Callback)
                    String token = data.getStringExtra("data");                 // Token response
                    String phoneNumber = data.getStringExtra("phonenumber");    // Phonenumber
                    String partnerRefId = UUID.randomUUID().toString();               // Mã đơn hàng
                    String requestId = String.valueOf(System.currentTimeMillis());    // RequestID
                    if (data.getStringExtra("data") != null && !data.getStringExtra("data").equals("")) {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                        MoMoPayment moMoPayment = new MoMoPayment();

                        // Tiến hành thanh toán, mô tả:
                        // Bước 1: Ứng dụng phía User sử dụng MoMo Mobile SDK để mở ứng dụng MoMo yêu cầu thanh toán. (requestPayment())
                        // Bước 2: Sau khi User xác nhận thanh toán ở MoMo, MoMo sẽ mở lại ứng dụng kèm thông tin xác nhận (callback). (onActivityResult())
                        // Bước 3: Ứng dụng User kiểm tra kết quả nhận được từ callback, nếu thành công thì gửi thông tin thanh toán qua payment() ở MoMoPayment
                        // Bước 4: payment() User xác thực và gửi yêu cầu thanh toán đầy đủ đến server MoMo để tiến hành treo tiền User.
                        // Bước 5: Server MoMo xử lý và trả kết quả treo tiền về User. Nếu thất bại (không thể treo tiền User), giao dịch kết thúc tại đây.
                        // Bước 6: User nhận kết quả trả về từ server MoMo, xử lý và trả kết quả thanh toán cho người dùng (Xử lý qua confirmPayment() ở MoMoPayment). Tùy vào kết quả của quá trình xử lý (thành công hoặc thất bại) mà gửi request tương ứng tới server MoMo để confirm giao dịch (commit hoặc rollback).
                        // Bước 7: Server MoMo xử lý yêu cầu confirm và trả kết quả về cho User.

                        // Bước 3:
                        moMoPayment.payment(WalletUserActivity.this, Long.parseLong(String.valueOf(amount)), partnerRefId, phoneNumber, token, requestId, new MoMoPayment.PaymentCallback() {
                            @Override
                            public void onPaymentSuccess(ResponseConfirmPayment responseConfirmPayment) {

                                // Cộng tiền nạp vào Firebase
                                userRef.child(user).child("wallet").child("money").setValue(money + responseConfirmPayment.getData().getAmount());
                                userRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        tvMoney.setText("Số dư: " + numberFormat.format(snapshot.child(user).child("wallet").child("money").getValue(long.class)));
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                // Lấy thời gian hiện tại
                                LocalDateTime currentDateTime = LocalDateTime.now();

                                // Định dạng ngày giờ
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
                                String formattedDateTime = currentDateTime.format(formatter);

                                Transaction transaction = new Transaction(responseConfirmPayment.getData().getPartnerRefId(), formattedDateTime, responseConfirmPayment.getData().getAmount());
                                userRef.child(user).child("wallet/transaction").push().setValue(transaction);


//                                moMoPayment.queryStatus(WalletUserActivity.this, partnerRefId, requestId, new MoMoPayment.QueryStatusCallback() {
//                                    @Override
//                                    public void onQueryStatusSuccess(ResponseQueryStatus responseQueryStatus) {
//                                        Transaction transaction = new Transaction(responseQueryStatus.getData().getResponseDate(), responseQueryStatus.getData().getAmount());
//                                        userRef.child(user).child("wallet").child("transaction").child(responseQueryStatus.getData().getBillId()).setValue(transaction);
//                                    }
//
//                                    @Override
//                                    public void onQueryStatusFailure(String errorMessage) {
//
//                                    }
//                                });
                            }

                            @Override
                            public void onPaymentSuccess(long amount) {

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