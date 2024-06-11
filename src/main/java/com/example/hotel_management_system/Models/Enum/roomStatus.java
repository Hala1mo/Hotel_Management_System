package com.example.hotel_management_system.Models.Enum;

public enum  roomStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("Reserved"),
    CHECKED_OUT(" Checked_Out"),
    CHECKED_IN(" Checked_In");

    private final String value;
    roomStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    }