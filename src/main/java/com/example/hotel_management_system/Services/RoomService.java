package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.InsertRoomDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {
    List<RoomDTO> retrieveRooms();
    RoomDTO findRoomById(long id);
    RoomDTO updateRoomById(long id,InsertRoomDTO requestedRoom);
    ResponseEntity<?> saveNewRoom (InsertRoomDTO requestedRoom);
    List<RoomDTO>retrieveRoomsBySpecificStatus(roomStatus status);
    List<RoomDTO>retrieveRoomsBySpecificView(roomView view);

}
