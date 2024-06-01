package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/rooms/features")
public class FeatureController {
    //update feature
    RoomFeaturesService roomFeaturesService;

    @Autowired
    public FeatureController( RoomFeaturesService roomFeaturesService){
        this.roomFeaturesService=roomFeaturesService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public List<FeatureDTO> retrieveFeatures(){

        return roomFeaturesService.retrieveFeatures();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public FeatureDTO saveFeatures(@RequestBody FeatureDTO request){
        return roomFeaturesService.saveFeatures(request);
    }

}
