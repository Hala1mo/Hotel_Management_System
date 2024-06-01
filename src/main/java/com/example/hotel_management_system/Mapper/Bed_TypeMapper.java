package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.Bed_TypeDTO;
import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.Models.Bed_Type;
import com.example.hotel_management_system.Models.Features;

public class Bed_TypeMapper {
    public static Bed_TypeDTO mapToDTO(Bed_Type request){
       Bed_TypeDTO bed_type=new Bed_TypeDTO();
       bed_type.setBed_type(request.getBed_type());
       bed_type.setId(request.getId());
       return  bed_type;
    }
    ////////////////////////////////
    public static void update (Features feature , Features request) {
        feature.setId(request.getId());
        feature.setDescription(request.getDescription());
        feature.setFeature(request.getFeature());
    }
    public static Bed_Type ToEntity(Bed_TypeDTO request){
        return  Bed_Type.builder()
                .bed_type(request.getBed_type())
                .build();
    }
}
