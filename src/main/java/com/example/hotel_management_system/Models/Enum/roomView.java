package com.example.hotel_management_system.Models.Enum;

public enum roomView {
POOL("POOL"),
GARDEN("GARDEN"),
    SEA("SEA");
    private final String value;
    roomView(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}