package com.tw;

import com.tw.exception.DivideByZeroException;

public class Calculator {
    public double add(double number1, double number2) {
        return number1 + number2;
    }

    public double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public double divide(double number1, double number2) throws DivideByZeroException {
        if (number2 == 0)
            throw new DivideByZeroException("Can not divide by zero");
        return number1 / number2;
    }
}
