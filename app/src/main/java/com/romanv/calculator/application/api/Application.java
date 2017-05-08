package com.romanv.calculator.application.api;

public interface Application {

    void execute(Command command);

    void subscribeToInputChange(Callback<String> callback);

    void subscribeToOutputChange(Callback<String> callback);

    CommandsFactory getCommandsFactory();

}
