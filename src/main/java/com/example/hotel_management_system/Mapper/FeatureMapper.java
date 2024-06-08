package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.Room.FeatureDTO;
import com.example.hotel_management_system.Models.Features;

public class FeatureMapper {
    public static FeatureDTO mapToDTO(Features request){
        FeatureDTO feature=new FeatureDTO ();
        feature.setId(request.getId());
        feature.setFeature(request.getFeature());
        feature.setDescription(request.getDescription());
        return feature;
    }
    public static void update (Features featureToBeUpdated , FeatureDTO request) {
        featureToBeUpdated.setId(request.getId());
        featureToBeUpdated.setDescription(request.getDescription());
        featureToBeUpdated.setFeature(request.getFeature());
    }
    public static Features ToEntity(FeatureDTO request){
        return  Features.builder().description(request.getDescription())
                .feature(request.getFeature())
                .build();
    }
}
