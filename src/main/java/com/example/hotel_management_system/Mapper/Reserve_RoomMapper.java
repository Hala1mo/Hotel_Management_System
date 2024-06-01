package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.*;
import com.example.hotel_management_system.Models.*;

public class Reserve_RoomMapper {
    public static Reserve_Room toEntity(Room room , Reservation reservation ) {
        System.out.print(room.getFloor_number());

        return Reserve_Room.builder()
                .room_id(room)
                .booking(reservation)
                .build();
    }
    public static Reserve_Room update(Room room , Reservation reservation ,Reserve_Room reserve_room) {
        reserve_room.setRoom_id(room);
        reserve_room.setBooking(reservation);
        return reserve_room;
    }


    public static Reserve_RoomDTO mapToDTO(Reserve_Room request){
        Reserve_RoomDTO Reserve_Room =new Reserve_RoomDTO();
        Reserve_Room.setId(request.getId());
        Reserve_Room.setRoom_id(request.getRoom_id().getId());
        return Reserve_Room;
    }
   public static RoomDTO mapToRoom (Reserve_Room request){
       RoomDTO room =new RoomDTO();
       room.setRoom_number(request.getRoom_id().getRoom_number());
       room.setFloor_number(request.getRoom_id().getFloor_number());
       room.setStatus(request.getRoom_id().getStatus());
       room.setRoom_Type(request.getRoom_id().getRoomType().getType_name());
       room.setView(request.getRoom_id().getView());
       room.setId(request.getRoom_id().getId());
        return room;
    }
    public static ReservationInfoDTO mapToBooking (Reserve_Room request){
        ReservationInfoDTO reservation =new ReservationInfoDTO();
        reservation.setNum_adults(request.getBooking().getNum_adults());
        reservation.setNum_children(request.getBooking().getNum_children());
        reservation.setCheckInDate(request.getBooking().getCheckInDate());
        reservation.setCheckOutDate(request.getBooking().getCheckOutDate());
        reservation.setId(request.getBooking().getId());
        reservation.setPaymentMethod(request.getBooking().getPayment_Method());
        reservation.setUser_id(request.getBooking().getUser().getId());
        return reservation;
    }

}
