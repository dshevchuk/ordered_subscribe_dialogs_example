package com.test.infopulse.http;

import com.test.infopulse.http.pojo.EmailValidationResult;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiClient implements ApiClient {

    private static final String TAG = "RetrofitApiClient";

    private static final String ENDPOINT = "http://apilayer.net/api/";
    private static final String ACCESS_KEY = "110ee3a710ab0655d727013f7481e325";

    private RetrofitApiService service;

    public RetrofitApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RetrofitApiService.class);
    }

    private OkHttpClient getHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Override
    public void validateEmail(String email, Callback<EmailValidationResult> callback) {
        Call<EmailValidationResult> call = service.validateEmail(ACCESS_KEY, email);
        call.enqueue(callback);
    }

}
