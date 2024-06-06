package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Room.Bed_TypeDTO;
import com.example.hotel_management_system.Services.BedTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/rooms/BedType")
public class RoomBedTypeController {
    BedTypeService bedTypeService;
    @Autowired
    public RoomBedTypeController(BedTypeService bedTypeService){
        this.bedTypeService=bedTypeService;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public List<Bed_TypeDTO> retrieveBedTypes(){

        return bedTypeService.retrieveAllBedTypes();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Bed_TypeDTO saveBedType(@Valid @RequestBody Bed_TypeDTO request){
        return bedTypeService.saveBedType(request);
    }



}
