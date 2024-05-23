package com.example.hotel_management_system.Models.Enum;

public enum  roomStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    DIRTY("Dirty"),
    MAINTENANCE("Maintence"),
    RESERVED("Reserved"),
    CHECKED_OUT(" Checked_Out");

    private final String value;
    roomStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    }