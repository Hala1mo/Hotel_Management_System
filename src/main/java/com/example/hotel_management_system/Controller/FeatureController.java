package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Features")
public class FeatureController {
    RoomFeaturesService roomFeaturesService;
    @Autowired
    public FeatureController( RoomFeaturesService roomFeaturesService){
        this.roomFeaturesService=roomFeaturesService;
    }
    @GetMapping("")
    public List<FeatureDTO> retrieveFeatures(){
        return roomFeaturesService.retrieveFeatures();
    }
    @PostMapping("")
    public FeatureDTO saveFeatures(@RequestBody FeatureDTO request){
        return roomFeaturesService.saveFeatures(request);
    }

}
