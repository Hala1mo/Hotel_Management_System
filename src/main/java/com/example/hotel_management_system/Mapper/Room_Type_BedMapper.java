package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.Room.Bed_TypeDTO;
import com.example.hotel_management_system.DTO.Room.RoomTypeDTO;
import com.example.hotel_management_system.Models.*;

public class Room_Type_BedMapper {
    public static Room_Type_Bed toEntity(Room_Type room_type, Bed_Type  bed_type,int num_of_beds) {

        return Room_Type_Bed.builder()
                .type_id(room_type)
                .bed_id(bed_type)
                .num_beds(num_of_beds)
                .build();
    }



    public static Bed_TypeDTO mapToDTOBed(Room_Type_Bed request){
        Bed_TypeDTO bed_type =new Bed_TypeDTO();
        bed_type.setId(request.getId());
        bed_type.setBed_type(request.getBed_id().getBed_type());
        return bed_type;
    }


}
