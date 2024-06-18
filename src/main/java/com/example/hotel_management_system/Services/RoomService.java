package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Room.InsertRoomDTO;
import com.example.hotel_management_system.DTO.ReservationInfoDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsInfoDTO;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface RoomService {
    List<RoomDTO> retrieveRooms();
    RoomDTO findRoomById(long id);

    RoomDTO updateRoomById(long id,InsertRoomDTO requestedRoom);
    ResponseEntity<?> saveNewRoom (InsertRoomDTO requestedRoom);
    List<RoomDetailsInfoDTO>retrieveRoomsBySpecificStatus(roomStatus status);
    List<RoomDTO>retrieveRoomsBySpecificView(roomView view);
    List<RoomDTO>retrieveAvailableRoomsBySpecificView(roomView view);
    List<RoomDetailsInfoDTO>retrieveRoomsBySpecificDates(Date checkIn, Date checkOut);
    List<ReservationInfoDTO>retrieveReservationForSpecificRoom (long id );
    List<RoomDTO> getRoomsByCleanStatus(String status);
}
