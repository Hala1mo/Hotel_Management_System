package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.*;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDTO> retrieveRoomsTypes();
    ResponseEntity<?> addFeatureForSpecificRoomType(Room_Type_FeatureDTO request);
    ResponseEntity<?> addBedTypeForSpecificRoomType(Room_Type_BedDTO request);
    List<FeatureDTO> retrieveFeaturesForSpecificRoomType(long id);
    RoomTypeDTO saveRoomType (RoomTypeDTO request);

}
