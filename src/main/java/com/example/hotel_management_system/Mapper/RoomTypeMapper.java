package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Models.Room_Type;

public class RoomTypeMapper {

    public static RoomTypeDTO mapToDTO(Room_Type roomtypeRequest){
        RoomTypeDTO roomtype=new RoomTypeDTO();
        roomtype.setId(roomtypeRequest.getId());
        roomtype.setType_name(roomtypeRequest.getType_name());
        roomtype.setPrice(roomtypeRequest.getPrice());
        return roomtype;
    }
    public static void update (Room_Type roomtype , Room_Type roomtypeRequest) {
        roomtype.setId(roomtypeRequest.getId());
        roomtype.setType_name(roomtypeRequest.getType_name());
        roomtype.setPrice(roomtypeRequest.getPrice());

    }
    public static Room_Type ToEntity(RoomTypeDTO roomtypeRequest){
        return Room_Type.builder().type_name(roomtypeRequest.getType_name())
                .price(roomtypeRequest.getPrice())
                .build();

    }

}
