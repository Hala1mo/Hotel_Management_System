package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.DTO.Room_Type_FeatureDTO;
import com.example.hotel_management_system.Models.Features;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Models.Room_Type;
import com.example.hotel_management_system.Models.Room_Type_Feature;

public class Room_Type_FeatureMapper {
    public static Room_Type_Feature toEntity(Room_Type room_type, Features feature) {

        return Room_Type_Feature.builder()
                .type_id(room_type)
                .feature_id(feature)
                .build();
    }

    public static RoomTypeDTO mapToDTORoom_Type( Room_Type_Feature request){
        RoomTypeDTO room_type=new RoomTypeDTO();
        room_type.setId(request.getType_id().getId());
        room_type.setType_name(request.getType_id().getType_name());
        room_type.setPrice(request.getType_id().getPrice());
        return room_type;
    }
    public static FeatureDTO mapToDTOFeature(Room_Type_Feature request){
        FeatureDTO feature =new FeatureDTO();
        feature.setId(request.getId());
        feature.setFeature(request.getFeature_id().getFeature());
        feature.setDescription(request.getFeature_id().getDescription());
        return feature;
    }


}
