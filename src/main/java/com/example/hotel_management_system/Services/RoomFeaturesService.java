package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Room.FeatureDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomFeaturesService {
    List<FeatureDTO> retrieveFeatures();
    FeatureDTO saveFeatures (FeatureDTO request);
    ResponseEntity<?> updateFeatures (FeatureDTO request, long id);
    ResponseEntity<?> deleteFeature (long id);

}
