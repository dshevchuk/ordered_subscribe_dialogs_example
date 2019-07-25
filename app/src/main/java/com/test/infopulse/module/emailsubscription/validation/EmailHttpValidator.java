package com.test.infopulse.module.emailsubscription.validation;

import com.test.infopulse.App;
import com.test.infopulse.Settings;
import com.test.infopulse.http.ApiClient;
import com.test.infopulse.http.pojo.EmailValidationResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailHttpValidator implements Validator<String> {

    private ApiClient apiClient;

    public EmailHttpValidator() {
        // it would be better to do dependency injection here
        // but for now it should be enough
        apiClient = App.getInstance().getApiClient();
    }

    @Override
    public void validation(String data, ValidationCallback callback) {
        App.getInstance().getApiClient().validateEmail(data, new Callback<EmailValidationResult>() {
            @Override
            public void onResponse(Call<EmailValidationResult> call, Response<EmailValidationResult> response) {
                if (response.isSuccessful()) {
                    EmailValidationResult result = response.body();

                    if (callback != null) {
                        if (result != null && result.getFormatValid()) {
                            callback.onSuccess();
                        } else {
                            callback.onFail("You input wrong email. Try next time");
                        }
                    }
                } else {
                    if (callback != null) {
                        callback.onFail("Verify oemail request failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<EmailValidationResult> call, Throwable t) {
                if (callback != null) {
                    callback.onError(t);
                }
            }
        });
    }
}
