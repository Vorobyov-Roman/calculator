package com.romanv.calculator.application.api;

public interface BinaryOperator<T> {

    T evaluate(T left, T right);

}
