package com.example.hotel_management_system.controllers;


import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.Services.EmployeeService;
import com.example.hotel_management_system.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @PostMapping("/create")
    public RoomDTO createRoom(@RequestBody RoomDTO room) {
        return roomService.createRoom(room);
    }

    @GetMapping("/id/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/cleanliness/{status}")
    public List<RoomDTO> getRoomByCleanStatus(@PathVariable String status) {
        return roomService.getRoomsByCleanStatus(status);
    }



}
