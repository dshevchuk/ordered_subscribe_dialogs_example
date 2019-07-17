package com.test.infopulse.http;


import com.test.infopulse.http.pojo.EmailValidationResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiService {
    @GET("/check")
    Call<EmailValidationResult> validateEmail(@Query("access_key") String accessKey,
                                              @Query("email") String email);
}
