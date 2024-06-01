package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.DTO.Room_Type_BedDTO;
import com.example.hotel_management_system.DTO.Room_Type_FeatureDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDTO> retrieveRoomsTypes();
    ResponseEntity<?> addFeatureForSpecificRoomType(Room_Type_FeatureDTO request);
    ResponseEntity<?> addBedTypeForSpecificRoomType(Room_Type_BedDTO request);
    List<FeatureDTO> retrieveFeaturesForSpecificRoomType(long id);
    RoomTypeDTO saveRoomType (RoomTypeDTO request);

}
