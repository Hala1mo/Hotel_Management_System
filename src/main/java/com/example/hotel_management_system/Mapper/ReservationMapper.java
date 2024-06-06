package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.Models.*;
import com.example.hotel_management_system.Models.Enum.reservationStatus;

import java.util.stream.Collectors;

public class ReservationMapper {
    public static ReservationDTO mapToDTO(Reservation request){
        ReservationDTO reservation=new ReservationDTO();
        reservation.setId(request.getId());
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setNum_adults(request.getNum_adults());
        reservation.setNum_children(request.getNum_children());
        reservation.setUser_id(request.getUser().getId());
        reservation.setPaymentMethod(request.getPayment_Method());
        reservation.setBooking_room(request.getBooking_room().stream().map(room -> Reserve_RoomMapper.mapToDTO(room)).collect(Collectors.toList()));
        return reservation;
    }
    public static Reservation update (Reservation reservation,ReservationDTO request,User user,reservationStatus status){
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setNum_adults(request.getNum_adults());
        reservation.setNum_children(request.getNum_children());
        reservation.setUser(user);
        reservation.setStatus(status);
        reservation.setPayment_Method(request.getPaymentMethod());
        return reservation;
    }



}
