package com.tw;

import java.util.Objects;

public class Operand implements Token {
    private final String value;

    private Operand(String token) {
        value = token;
    }

    public static Operand of(String value) {
        return new Operand(value);
    }

    @Override
    public boolean isOperator() {
        return false;
    }

    @Override
    public boolean isOperand() {
        return true;
    }

    @Override
    public String getValue() {
        return value;
    }

    // does not belong here; need refactoring
    @Override
    public int precedence() {return -11111;}

    @Override
    public String toString() {
        return "Operand{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operand operand = (Operand) o;
        return Objects.equals(value, operand.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
