package com.example.hotel_management_system.Models.Enum;

public enum reservationStatus {
    Confirmed("Confirmed"),
    Pending("Pending"),
    pendingCancellationApproval("pendingCancellationApproval"),
    Canceled("Canceled"),
    Checkin("Checkin"),
    Checkout("Checkout");

    private final String value;
    reservationStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
