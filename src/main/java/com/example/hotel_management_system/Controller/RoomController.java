package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.*;
import com.example.hotel_management_system.DTO.Room.InsertRoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsInfoDTO;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bedTypes")
    public List<RoomDTO> retrieveBedTypes(){
        return roomService.retrieveRooms();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    public List<RoomDetailsInfoDTO> retrieveRoomsBySpecificStatus(@PathVariable roomStatus status){
        return roomService.retrieveRoomsBySpecificStatus(status);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/view/{view}")
    public List<RoomDTO>retrieveRoomsBySpecificView(@PathVariable roomView view){
        return roomService.retrieveRoomsBySpecificView(view);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/available-rooms")
    public List<RoomDetailsInfoDTO> getAvailableRooms(
            @RequestParam("check-in-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @RequestParam("check-out-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate) {
        List<RoomDetailsInfoDTO> availableRooms = roomService.retrieveRoomsBySpecificDates(checkInDate,checkOutDate);
        return availableRooms;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reservations/{id}")
    public List<ReservationInfoDTO> retrieveReservationForSpecificRoom (@PathVariable long id ) {
        return roomService.retrieveReservationForSpecificRoom(id);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoomDTO> getAllRooms() {
        return roomService.retrieveRooms();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> saveNewRoom (@Valid @RequestBody InsertRoomDTO requestedRoom){
        return roomService.saveNewRoom(requestedRoom);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/id/{id}")
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.findRoomById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("update/id/{id}")
    public RoomDTO updateRoom(@PathVariable Long id,@Valid @RequestBody InsertRoomDTO requestedRoom) {
        return roomService.updateRoomById(id,requestedRoom);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cleanliness/{status}")
    public List<RoomDTO> getRoomByCleanStatus(@PathVariable String status) {
        return roomService.getRoomsByCleanStatus(status);
    }



}
