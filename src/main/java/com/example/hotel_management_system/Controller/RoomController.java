package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Bed_TypeDTO;
import com.example.hotel_management_system.DTO.InsertRoomDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Mapper.RoomMapper;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Services.BedTypeService;
import com.example.hotel_management_system.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/rooms")
public class RoomController {
    RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }
    @GetMapping("")
    public List<RoomDTO> retrieveBedTypes(){
        return roomService.retrieveRooms();
    }
    @GetMapping("/status/{status}")
    public List<RoomDTO> retrieveRoomsBySpecificStatus(@PathVariable roomStatus status){
        return roomService.retrieveRoomsBySpecificStatus(status);
    }
    @GetMapping("/view/{view}")
    public List<RoomDTO>retrieveRoomsBySpecificView(@PathVariable roomView view){
        return roomService.retrieveRoomsBySpecificView(view);
    }
    @PostMapping("")
    public ResponseEntity<?> saveNewRoom (@RequestBody  InsertRoomDTO requestedRoom){
        return roomService.saveNewRoom(requestedRoom);
    }





}
