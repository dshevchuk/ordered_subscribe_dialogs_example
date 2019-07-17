package com.test.infopulse.http;


import com.test.infopulse.http.pojo.EmailValidationResult;

import retrofit2.Callback;

public interface ApiClient {
    void validateEmail(String email, Callback<EmailValidationResult> callback);
}

