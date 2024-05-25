package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.Models.Features;
import com.example.hotel_management_system.Models.Reservation;

public class ReservationMapper {
    public static ReservationDTO mapToDTO(Reservation request){
        ReservationDTO reservation=new ReservationDTO();
        reservation.setId(request.getId());
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setNum_adults(request.getNum_adults());
        reservation.setNum_children(request.getNum_children());
        reservation.setUser_id(request.getId());
        return reservation;
    }

}
