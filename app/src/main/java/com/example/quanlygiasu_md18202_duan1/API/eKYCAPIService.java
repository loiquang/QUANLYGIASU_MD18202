package com.example.quanlygiasu_md18202_duan1.API;

import com.example.quanlygiasu_md18202_duan1.Models.users.Infor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface eKYCAPIService {

    // https://api.fpt.ai/vision/idr/vnm
    public static final String BASE_URL = "https://api.fpt.ai/";

    Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();

    eKYCAPIService ekycApiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(eKYCAPIService.class);

    @Headers({"api-key: PfIN6Fm2xRXXQN4Z2dRH6ZLOQZrO6NPc"})
    @Multipart
    @POST("vision/idr/vnm")
    Call<Infor> getInforCCCD(@Part MultipartBody.Part image, @Part MultipartBody.Part face);
}
