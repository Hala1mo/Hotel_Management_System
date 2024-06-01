package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Models.Room;

import java.util.List;

public interface RoomService {
        RoomDTO getRoomById(long id);
        RoomDTO createRoom(RoomDTO roomDTO);
        List<RoomDTO> getAllRooms();

        List<RoomDTO> getRoomsByCleanStatus(String status);
       RoomDTO updateRoom(long id, RoomDTO roomDTO);


}
