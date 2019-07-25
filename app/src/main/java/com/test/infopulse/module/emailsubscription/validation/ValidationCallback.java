package com.test.infopulse.module.emailsubscription.validation;

public interface ValidationCallback {
    void onSuccess();
    void onFail(String message);
    void onError(Throwable t);
}
