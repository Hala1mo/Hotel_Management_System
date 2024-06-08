package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Room.FeatureDTO;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/create")
    public FeatureDTO saveFeatures( @Valid @RequestBody FeatureDTO request){
        return roomFeaturesService.saveFeatures(request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public ResponseEntity<?> updateFeatures(@Valid @RequestBody FeatureDTO request, @PathVariable long id){
        return roomFeaturesService.updateFeatures(request,id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFeatures(@PathVariable long id){
        return roomFeaturesService.deleteFeature(id);
    }

}
