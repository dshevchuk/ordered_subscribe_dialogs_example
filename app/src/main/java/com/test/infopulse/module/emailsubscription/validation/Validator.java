package com.test.infopulse.module.emailsubscription.validation;

public interface Validator<T> {
    void validation(T data, ValidationCallback callback);
}
