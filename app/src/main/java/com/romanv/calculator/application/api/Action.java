package com.romanv.calculator.application.api;

public enum Action {

    One("1"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Zero("0"),

    Add("+"),
    Subtract("-"),
    Multiply("*"),
    Divide("/"),

    Point("."),

    Delete(null),
    Clear(null);

    Action(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return this.str;
    }

    private String str;

}
