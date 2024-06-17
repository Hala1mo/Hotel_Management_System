package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.Room.RoomTypeDTO;
import com.example.hotel_management_system.Models.Room_Type;

public class RoomTypeMapper {

    public static RoomTypeDTO mapToDTO(Room_Type roomtypeRequest){
        RoomTypeDTO roomtype=new RoomTypeDTO();
        roomtype.setId(roomtypeRequest.getId());
        roomtype.setType_name(roomtypeRequest.getType_name());
        roomtype.setPrice(roomtypeRequest.getPrice());
        roomtype.setSize_room(roomtypeRequest.getSize_room());
        roomtype.setNum_adults(roomtypeRequest.getNum_adults());
        roomtype.setNum_children(roomtypeRequest.getNum_children());
        return roomtype;
    }
    public static Room_Type update (Room_Type roomtype , RoomTypeDTO roomtypeRequest) {
        roomtype.setType_name(roomtypeRequest.getType_name());
        roomtype.setPrice(roomtypeRequest.getPrice());
        roomtype.setNum_adults(roomtypeRequest.getNum_adults());
        roomtype.setNum_children(roomtypeRequest.getNum_children());
        roomtype.setSize_room(roomtypeRequest.getSize_room());
return roomtype;
    }
    public static Room_Type ToEntity(RoomTypeDTO roomtypeRequest){
        return Room_Type.builder().type_name(roomtypeRequest.getType_name())
                .price(roomtypeRequest.getPrice())
                .num_adults(roomtypeRequest.getNum_adults())
                .num_children(roomtypeRequest.getNum_children())
                .size_room(roomtypeRequest.getSize_room())
                .build();
    }

}
