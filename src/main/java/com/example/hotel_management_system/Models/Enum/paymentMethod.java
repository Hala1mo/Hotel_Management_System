package com.example.hotel_management_system.Models.Enum;

public enum paymentMethod {

    PAY_NOW("PAY_NOW"),
    PAY_LATER("PAY_LATER");


    private final String value;
    paymentMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
