package com.test.infopulse;

import android.app.Application;

import com.test.infopulse.http.ApiClient;
import com.test.infopulse.http.RetrofitApiClient;

public class App extends Application {

    public static App instance;

    private ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        apiClient = new RetrofitApiClient();
    }

    public static App getInstance() {
        return instance;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

}
