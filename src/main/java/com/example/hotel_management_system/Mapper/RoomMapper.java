package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.Room;

public class RoomMapper {
    public static RoomDTO mapToDTO(Room room) {
        RoomDTO RoomDTO = new RoomDTO();
        RoomDTO.setCleanlinessStatus(room.getCleanlinessStatus());
        RoomDTO.setID(room.getId());
        return RoomDTO;
    }

    // convert DTO to entity
    public static Room mapToEntity(RoomDTO roomDTO) {
        Room room = new Room();

        room.setCleanlinessStatus(roomDTO.getCleanlinessStatus());
        return room;
    }
}
