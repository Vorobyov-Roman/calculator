package com.romanv.calculator.application.api;

public interface Callback<T> {

    void call(T value);

}
