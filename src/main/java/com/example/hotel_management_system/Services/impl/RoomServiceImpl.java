package com.example.hotel_management_system.Services.impl;

import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Mapper.EmployeeMapper;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Services.RoomService;
import com.example.hotel_management_system.repositories.EmployeeRepository;
import com.example.hotel_management_system.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Override
    public Room getRoomrById(long id) {
        Room room = roomRepository.findById(id);
        return room;
    }
}
