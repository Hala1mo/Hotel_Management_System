package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.Bed_TypeDTO;
import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.DTO.Room_Type_FeatureDTO;
import com.example.hotel_management_system.Models.*;

public class Room_Type_BedMapper {
    public static Room_Type_Bed toEntity(Room_Type room_type, Bed_Type  bed_type,int num_of_beds) {

        return Room_Type_Bed.builder()
                .type_id(room_type)
                .bed_id(bed_type)
                .num_beds(num_of_beds)
                .build();
    }

    public static RoomTypeDTO mapToDTORoom_Type( Room_Type_Bed request){
        RoomTypeDTO room_type=new RoomTypeDTO();
        room_type.setId(request.getType_id().getId());
        room_type.setType_name(request.getType_id().getType_name());
        room_type.setPrice(request.getType_id().getPrice());
        return room_type;
    }
    public static Bed_TypeDTO mapToDTOFeature(Room_Type_Bed request){
        Bed_TypeDTO bed_type =new Bed_TypeDTO();
        bed_type.setId(request.getId());
        bed_type.setBed_type(bed_type.getBed_type());
        return bed_type;
    }


}
