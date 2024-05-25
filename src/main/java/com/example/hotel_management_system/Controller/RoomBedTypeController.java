package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Bed_TypeDTO;
import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Services.BedTypeService;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/BedType")
public class RoomBedTypeController {
    BedTypeService bedTypeService;
    @Autowired
    public RoomBedTypeController( BedTypeService bedTypeService){
        this.bedTypeService=bedTypeService;
    }
    @GetMapping("")
    public List<Bed_TypeDTO> retrieveBedTypes(){

        return bedTypeService.retrieveAllBedTypes();
    }
    @PostMapping("")
    public Bed_TypeDTO saveBedType(@RequestBody Bed_TypeDTO request){
        return bedTypeService.saveBedType(request);
    }

}
