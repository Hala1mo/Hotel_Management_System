package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.FeatureDTO;

import java.util.List;

public interface RoomFeaturesService {
    List<FeatureDTO> retrieveFeatures();
    FeatureDTO saveFeatures (FeatureDTO request);
}
