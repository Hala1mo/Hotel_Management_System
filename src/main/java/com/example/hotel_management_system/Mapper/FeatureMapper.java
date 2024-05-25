package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Models.Features;
import com.example.hotel_management_system.Models.Room_Type;

public class FeatureMapper {
    public static FeatureDTO mapToDTO(Features request){
        FeatureDTO feature=new FeatureDTO ();
        feature.setId(request.getId());
        feature.setFeature(request.getFeature());
        feature.setDescription(request.getDescription());
        return feature;
    }
    public static void update (Features feature , Features request) {
        feature.setId(request.getId());
        feature.setDescription(request.getDescription());
        feature.setFeature(request.getFeature());
    }
    public static Features ToEntity(FeatureDTO request){
        return  Features.builder().description(request.getDescription())
                .feature(request.getFeature())
                .build();
    }
}
