package com.romanv.calculator.application.api;

public interface Callback<T> {

    public void call(T value);

}
