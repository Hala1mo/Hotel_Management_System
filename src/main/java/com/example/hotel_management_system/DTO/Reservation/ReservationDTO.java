package com.example.hotel_management_system.DTO.Reservation;

import com.example.hotel_management_system.Models.Enum.paymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkInDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date checkOutDate;

    @NotNull
    private int num_adults;

    @NotNull
    private int num_children;

    @NotNull
    private List <Reserve_RoomDTO>  booking_room;
    @NotNull
    private List <Reserve_Add_OnDTO>  additions=new ArrayList<>(); ;

    @NotNull
    private paymentMethod paymentMethod;
}
