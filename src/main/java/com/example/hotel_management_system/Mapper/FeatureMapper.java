package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.Room.FeatureDTOv1;
import com.example.hotel_management_system.DTO.Room.FeatureDTOv2;
import com.example.hotel_management_system.DTO.Room.InsertFeatureDTO;
import com.example.hotel_management_system.Models.Features;

public class FeatureMapper {
    public static InsertFeatureDTO mapToDTO(Features request){
        InsertFeatureDTO feature=new InsertFeatureDTO();
        feature.setId(request.getId());
        feature.setFeature(request.getFeature());
        feature.setDescription(request.getDescription());
        return feature;
    }
    public static FeatureDTOv1 mapToDTOV1(Features request){
        FeatureDTOv1 feature=new FeatureDTOv1();
        feature.setId(request.getId());
        feature.setFeature(request.getFeature());
        return feature;
    }
    public static FeatureDTOv2 mapToDTOV2(Features request){
        FeatureDTOv2 feature=new FeatureDTOv2();
        feature.setId(request.getId());
        feature.setFeature(request.getFeature());
        feature.setDescription(request.getDescription());
        return feature;
    }

    public static void update (Features featureToBeUpdated , InsertFeatureDTO request) {
        featureToBeUpdated.setId(request.getId());
        featureToBeUpdated.setDescription(request.getDescription());
        featureToBeUpdated.setFeature(request.getFeature());
    }
    public static Features ToEntity(InsertFeatureDTO request){
        return  Features.builder().description(request.getDescription())
                .feature(request.getFeature())
                .build();
    }
}
