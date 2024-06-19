package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Room.FeatureDTOv1;
import com.example.hotel_management_system.DTO.Room.FeatureDTOv2;
import com.example.hotel_management_system.DTO.Room.InsertFeatureDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomFeaturesService {
    List<FeatureDTOv1> retrieveFeaturesV1();
    List<FeatureDTOv2> retrieveFeaturesV2();
    InsertFeatureDTO saveFeatures (InsertFeatureDTO request);
    ResponseEntity<?> updateFeatures (InsertFeatureDTO request, long id);
    ResponseEntity<?> deleteFeature (long id);

}
