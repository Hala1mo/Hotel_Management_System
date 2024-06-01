package com.example.hotel_management_system.DTO;

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
public class InvoiceDTO {

    private long id;

    @NotNull
    private long booking_id;
    @NotNull
    private double price;



}
