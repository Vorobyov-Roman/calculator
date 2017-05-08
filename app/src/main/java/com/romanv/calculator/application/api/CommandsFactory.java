package com.romanv.calculator.application.api;

public interface CommandsFactory {

    Command createCommand(ActionButton pressedButton);

}
