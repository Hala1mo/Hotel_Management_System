package com.example.hotel_management_system.Models.Enum;

public enum paymentMethod {
    CREDIT_CARD("Pay_Later"),
    DEBIT_CARD("Credit_CARD"),
    PAY_LATER("PAY_LATER"),
    CASH("CASH");

    private final String value;
    paymentMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
