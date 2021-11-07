package com.tw;

import com.tw.exception.DivideByZeroException;

import java.util.HashMap;
import java.util.Map;

// can be done by class inheritance too; but i feel enum is better suited here as operators is a closed set
public enum Operator implements Token, Operation {
    PLUS("+") {
        @Override
        public int precedence() {
            return 1;
        }

        @Override
        public double perform(double value1, double value2) {
            return value1 + value2;
        }
    },

    MINUS("-") {
        @Override
        public int precedence() {
            return 1;
        }

        @Override
        public double perform(double value1, double value2) {
            return value1 - value2;
        }
    },

    DIV("/") {
        @Override
        public int precedence() {
            return 2;
        }

        @Override
        public double perform(double value1, double value2) {
            if (value2 == 0)
                throw new DivideByZeroException("Can not divide by zero");
            return value1 / value2;
        }
    },

    MULTIPLY("*") {
        @Override
        public int precedence() {
            return 2;
        }

        @Override
        public double perform(double value1, double value2) {
            return value1 * value2;
        }
    };

    private final String value;

    private static final Map<String, Operator> operators;

    static {
        operators = new HashMap<>();
        for (Operator operator : Operator.values()) {
            operators.put(operator.value, operator);
        }
    }

    Operator(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static Operator get(String op) {
        return operators.get(op);
    }

    public static boolean isOperator(String op) {
        Operator operator = operators.get(op);
        return operator != null;
    }

    @Override
    public boolean isOperator() {
        return true;
    }

    @Override
    public boolean isOperand() {
        return false;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "value='" + value + '\'' +
                '}';
    }
}
