package com.example.quanlygiasu_md18202_duan1.MoMoController;

import static com.example.quanlygiasu_md18202_duan1.API.MoMoAPIService.BASE_URL;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.quanlygiasu_md18202_duan1.API.MoMoAPIService;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.RequestConfirmPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.RequestPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.RequestQueryStatus;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponseConfirmPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponsePayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponseQueryStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoMoPayment {

    public interface PaymentCallback {
        void onPaymentSuccess(ResponseConfirmPayment responseConfirmPayment);

        void onPaymentSuccess(long amount);

        void onPaymentFailure(String errorMessage);
    }

    public interface QueryStatusCallback {
        void onQueryStatusSuccess(ResponseQueryStatus responseQueryStatus);

        void onQueryStatusFailure(String errorMessage);
    }


    MoMoAPIService momoAPIService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoMoAPIService.class);

    public void payment(Activity activity, long amount, String partnerRefId, String phoneNumber, String token, String requestId, PaymentCallback callback) {

        // Bước 4:

        // Tạo dataHash bằng JSONObject
        JSONObject dataHash = new JSONObject();
        try {
            dataHash.put("partnerCode", MoMoConfig.MERCHANT_CODE);
            dataHash.put("partnerRefId", partnerRefId);
            dataHash.put("amount", amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Mã hóa dữ liệu JSON bằng publicKey RSA
        MoMoUtils moMoUtils = new MoMoUtils();
        String hash = moMoUtils.encryptDataWithRSA(dataHash.toString(), MoMoConfig.PUBLIC_KEY);
        RequestPayment requestPayment = new RequestPayment(MoMoConfig.MERCHANT_CODE, partnerRefId, phoneNumber, token, hash, 2.0, 3);
        momoAPIService.getPayment(requestPayment).enqueue(new Callback<ResponsePayment>() {
            @Override
            public void onResponse(Call<ResponsePayment> call, Response<ResponsePayment> response) {
                ResponsePayment responsePayment = response.body();

                // Bước 5: (Hiện vẫn còn lỗi văng app nếu như thất bại (không thể treo tiền User))
                if (responsePayment.getStatus() == 0) {
                    // Bước 6:
                    confirmPayment(activity, responsePayment.getTransid(), partnerRefId, requestId, callback);

                } else {
                    Toast.makeText(activity, responsePayment.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePayment> call, Throwable t) {

            }
        });

    }

    public void confirmPayment(Activity activity, String momoTransId, String partnerRefId, String requestId, PaymentCallback callback) {

        // Bước 6:

        // Tạo các trường dữ liệu còn thiếu cho requestConfirmPayment
        String signature = "";
        String signatureFormat = "partnerCode=" + MoMoConfig.MERCHANT_CODE + "&partnerRefId=" + partnerRefId + "&requestType=" + "capture" + "&requestId=" + requestId + "&momoTransId=" + momoTransId;

        MoMoUtils moMoUtils = new MoMoUtils();
        // Sử dụng thuật toán Hmac_SHA256 với data theo định dạng:
        // partnerCode=$partnerCode&partnerRefId=$partnerRefId&requestType=$requestType&requestId=$requestId&momoTransId=$momoTransId
        try {
            signature = moMoUtils.signHmacSHA256(signatureFormat, MoMoConfig.SECRET_KEY);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        RequestConfirmPayment requestConfirmPayment = new RequestConfirmPayment(MoMoConfig.MERCHANT_CODE, partnerRefId, "capture", requestId, momoTransId, signature);
        momoAPIService.getConfirmPayment(requestConfirmPayment).enqueue(new Callback<ResponseConfirmPayment>() {
            @Override
            public void onResponse(Call<ResponseConfirmPayment> call, Response<ResponseConfirmPayment> response) {
                ResponseConfirmPayment responseConfirmPayment = response.body();
                if (responseConfirmPayment.getStatus() == 0) {
                    // Bước 7:
                    callback.onPaymentSuccess(responseConfirmPayment);
                } else {
                    Toast.makeText(activity, responseConfirmPayment.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseConfirmPayment> call, Throwable t) {

            }
        });
    }

//    public void queryStatus(Activity activity, String partnerRefId, String requestId, QueryStatusCallback queryStatusCallback) {
//        // Tạo dataHash bằng JSONObject
//        JSONObject dataHash = new JSONObject();
//        try {
//            dataHash.put("requestId", requestId);
//            dataHash.put("partnerCode", MoMoConfig.MERCHANT_CODE);
//            dataHash.put("partnerRefId", partnerRefId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        MoMoUtils moMoUtils = new MoMoUtils();
//        // Mã hóa dữ liệu JSON bằng publicKey RSA
//        String hash = moMoUtils.encryptDataWithRSA(dataHash.toString(), MoMoConfig.PUBLIC_KEY);
//
//        RequestQueryStatus requestQueryStatus = new RequestQueryStatus(MoMoConfig.MERCHANT_CODE, partnerRefId, hash, 2);
//        momoAPIService.getQueryStatus(requestQueryStatus).enqueue(new Callback<ResponseQueryStatus>() {
//            @Override
//            public void onResponse(Call<ResponseQueryStatus> call, Response<ResponseQueryStatus> response) {
//                ResponseQueryStatus responseQueryStatus = response.body();
//                queryStatusCallback.onQueryStatusSuccess(responseQueryStatus);
//            }
//
//            @Override
//            public void onFailure(Call<ResponseQueryStatus> call, Throwable t) {
//
//            }
//        });
//
//    }

}
