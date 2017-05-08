package com.romanv.calculator.application.sources;

public class UnexpectedTokenException extends NumberFormatException {

    UnexpectedTokenException(NumberFormatException e) {
        super(e.getMessage());
    }

}
