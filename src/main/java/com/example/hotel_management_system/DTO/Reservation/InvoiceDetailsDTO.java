package com.example.hotel_management_system.DTO.Reservation;


import com.example.hotel_management_system.Models.Enum.reservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailsDTO {
    private long id;

    @NotNull
    private Date checkInDate;
    @NotNull
    private Date checkOutDate;

    @NotNull
    private int num_adults;

    @NotNull
    private int num_children;

    @NotNull
    private double totalPrice;
    @NotNull
    private reservationStatus statusOfReservation;


}
