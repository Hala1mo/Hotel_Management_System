package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.Models.*;

public class Reserve_Add_OnMapper {
    public static Reserve_Add_On toEntity(Reservation reservation,Add_On addon ) {

        return Reserve_Add_On.builder()
                .add_on_id(addon)
                .booking(reservation)
                .build();
    }
    public static Reserve_Add_On update(Reservation reservation,Add_On addon,Reserve_Add_On reserve_add_on) {
        reserve_add_on.setAdd_on_id(addon);
        reserve_add_on.setBooking(reservation);
       return reserve_add_on;
    }
}
