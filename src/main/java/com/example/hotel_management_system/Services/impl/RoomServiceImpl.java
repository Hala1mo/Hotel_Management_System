package com.example.hotel_management_system.Services.impl;

import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Mapper.RoomMapper;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Services.RoomService;
import com.example.hotel_management_system.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Override
    public RoomDTO getRoomById(long id) {
        Room room = roomRepository.findById(id);
        return RoomMapper.mapToDTO(room);
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = RoomMapper.mapToEntity(roomDTO);
        Room newRoom = roomRepository.save(room);
        return RoomMapper.mapToDTO(newRoom);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> queryResult = roomRepository.findAll();
        List<RoomDTO> rooms = new ArrayList<>();
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("No Rooms found in the database.");
        }
        for (Room employee : queryResult) {
            RoomDTO employeeDTO = RoomMapper.mapToDTO(employee);
            rooms.add(employeeDTO);
        }
        return rooms;
    }

    @Override
    public List<RoomDTO> getRoomsByCleanStatus(String status) {
        List<Room> queryResult = roomRepository.findAll();
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("No rooms found");
        }
        List<RoomDTO> rooms = new ArrayList<>();
        for (Room room : queryResult) {
            if(room.getCleanlinessStatus().toString().equals(status)) {
                RoomDTO employeeDTO = RoomMapper.mapToDTO(room);
                rooms.add(employeeDTO);
            }
        }
        return rooms;
    }

    @Override
    public RoomDTO updateRoom(long id, RoomDTO roomDTO) {
        return null;
    }
}
