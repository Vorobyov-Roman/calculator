package com.romanv.calculator.application;

import com.romanv.calculator.application.api.Environment;

public class TestEnvironment implements Environment {

    @Override
    public String getAdditionSymbol() {
        return "+";
    }

    @Override
    public String getSubtractionSymbol() {
        return "-";
    }

    @Override
    public String getMultiplicationSymbol() {
        return "*";
    }

    @Override
    public String getDivisionSymbol() {
        return "/";
    }

    @Override
    public String getPointSymbol() {
        return ".";
    }

}
