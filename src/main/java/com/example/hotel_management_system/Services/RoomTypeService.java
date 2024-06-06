package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Room.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDTO> retrieveRoomsTypes();
    ResponseEntity<?> addFeatureForSpecificRoomType(Room_Type_FeatureDTO request);
    ResponseEntity<?> addBedTypeForSpecificRoomType(Room_Type_BedDTO request);
    List<FeatureDTO> retrieveFeaturesForSpecificRoomType(long id);
    RoomTypeDTO saveRoomType (RoomTypeDTO request);
    List<Bed_TypeDTO> retrieveBedTypeForSpecificRoomType(Long id);

}
