package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/Reservations")
public class ReservationController {
    RoomTypeService roomTypeservice;

    @Autowired
    public ReservationController(RoomTypeService roomTypeservice) {
        this.roomTypeservice = roomTypeservice;
    }



}