package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.*;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms/RoomType")
public class Room_TypeController {
    RoomTypeService roomTypeservice;

    @Autowired
    public Room_TypeController(RoomTypeService roomTypeservice){
      this.roomTypeservice=roomTypeservice;
    }
    @GetMapping("")
    public List<RoomTypeDTO> retrieveRoomsTypes(){
        return roomTypeservice.retrieveRoomsTypes();
    }
    @GetMapping("Features/{id}")
    public List<FeatureDTO> retrieveFeaturesForSpecificRoomType(@PathVariable Long id){
        return roomTypeservice.retrieveFeaturesForSpecificRoomType(id);
    }
    @GetMapping("/Bed/{id}")
    public List<Bed_TypeDTO> retrieveBedTypeForSpecificRoomType(@PathVariable Long id){
        return roomTypeservice.retrieveBedTypeForSpecificRoomType(id);
    }
    @PostMapping("/create")
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
