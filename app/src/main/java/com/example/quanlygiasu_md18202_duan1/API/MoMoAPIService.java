package com.example.quanlygiasu_md18202_duan1.API;

import com.example.quanlygiasu_md18202_duan1.Models.MoMo.RequestConfirmPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.RequestPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.RequestQueryStatus;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponseConfirmPayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponsePayment;
import com.example.quanlygiasu_md18202_duan1.Models.MoMo.ResponseQueryStatus;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MoMoAPIService {

    // https://test-payment.momo.vn/pay/app
    public static final String BASE_URL = "https://test-payment.momo.vn/";

//    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
//    MoMoAPIService momoAPIService = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(MoMoAPIService.class);

    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("pay/app")
    Call<ResponsePayment> getPayment(@Body RequestPayment requestPayment);

    @POST("/pay/confirm")
    Call<ResponseConfirmPayment> getConfirmPayment(@Body RequestConfirmPayment requestConfirmPayment);

    @POST("/pay/query-status")
    Call<ResponseQueryStatus> getQueryStatus(@Body RequestQueryStatus requestQueryStatus);

}
