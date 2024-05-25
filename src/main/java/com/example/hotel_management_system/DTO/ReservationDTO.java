package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Enum.reservationStatus;
import com.example.hotel_management_system.Models.Reserve_Room;
import com.example.hotel_management_system.Models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReservationDTO {

    private long id;
    @NotNull
    private long user_id;
    @NotNull
    private Date checkInDate;
    @NotNull
    private Date checkOutDate;

    @NotNull
    private int num_adults;

    @NotNull
    private int num_children;
    @NotNull
    private double number_of_nights;
}
