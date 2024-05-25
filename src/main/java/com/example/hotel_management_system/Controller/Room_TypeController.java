package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.DTO.Room_Type_BedDTO;
import com.example.hotel_management_system.DTO.Room_Type_FeatureDTO;
import com.example.hotel_management_system.Models.Room_Type;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/RoomType")
public class Room_TypeController {
    RoomTypeService roomTypeservice;

    @Autowired
    public Room_TypeController( RoomTypeService roomTypeservice){
      this.roomTypeservice=roomTypeservice;
    }
    @GetMapping("")
    public List<RoomTypeDTO> retrieveRoomsTypes(){
        return roomTypeservice.retrieveRoomsTypes();
    }
    @GetMapping("{id}/Features")
    public List<FeatureDTO> retrieveFeaturesForSpecificRoomType(@PathVariable Long id){
        return roomTypeservice.retrieveFeaturesForSpecificRoomType(id);
    }
    @PostMapping("")
    public RoomTypeDTO saveRoomType(@RequestBody RoomTypeDTO request){
        return roomTypeservice.saveRoomType(request);
    }

    @PostMapping("/save/Features")
    public ResponseEntity<?> addFeatureForSpecificRoomType(@RequestBody Room_Type_FeatureDTO request){
        return roomTypeservice.addFeatureForSpecificRoomType(request);
    }
    @PostMapping("/save/Bed")
    public ResponseEntity<?> addBedTypeForSpecificRoomType(@RequestBody Room_Type_BedDTO request){
        return roomTypeservice.addBedTypeForSpecificRoomType(request);
    }
}
