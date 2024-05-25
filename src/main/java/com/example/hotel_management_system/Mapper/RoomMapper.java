package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.InsertRoomDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Models.Room_Type;

import java.util.List;

public class RoomMapper{

    public static RoomDTO mapToDTO(Room roomRequest){
        RoomDTO room=new RoomDTO();
        room.setId(roomRequest.getId());
        room.setRoom_number(roomRequest.getRoom_number());
        room.setFloor_number(roomRequest.getFloor_number());
        room.setRoom_Type(roomRequest.getRoomType().getType_name());
        room.setView(roomRequest.getView());
        room.setStatus(roomRequest.getStatus());
        return room;
    }
    public static void update (Room room ,InsertRoomDTO roomRequest,Room_Type type) {
        room.setId(roomRequest.getId());
        room.setRoom_number(roomRequest.getRoom_number());
        room.setFloor_number(roomRequest.getFloor_number());
        room.setRoomType(type);
        room.setView(roomRequest.getView());
        room.setStatus(roomRequest.getStatus());

    }
    public static Room ToEntity(InsertRoomDTO roomRequest, Room_Type room_type){
        System.out.println("eliana");
        System.out.println(roomRequest.getStatus());

        return Room.builder().room_number(roomRequest.getRoom_number())
                .status(roomRequest.getStatus())
                .view(roomRequest.getView())
                .floor_number(roomRequest.getFloor_number())
                .roomType(room_type)
                .build();

    }
    /*
    public static void update (Customer customerById , CustomerDto requestedCustomer) {
        customerById.setName(requestedCustomer.getName());
        customerById.setEmail(requestedCustomer.getEmail());
        customerById.setPhone_number(requestedCustomer.getPhone_number());

    }*/

}
