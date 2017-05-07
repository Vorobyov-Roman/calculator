package com.romanv.calculator.application.api;

public interface Application {

    public void perform(Action action);

    public void subscribeToInputChange(Callback<String> callback);

}
