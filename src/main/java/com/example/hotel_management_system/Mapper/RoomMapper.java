package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.Room.InsertRoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsInfoDTO;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Models.Room_Type;
import com.example.hotel_management_system.Models.Room_Type_Bed;
import com.example.hotel_management_system.Models.Room_Type_Feature;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper{

    public static RoomDTO mapToDTO(Room roomRequest){
        RoomDTO room=new RoomDTO();
        room.setId(roomRequest.getId());
        room.setRoom_number(roomRequest.getRoom_number());
        room.setRoom_Type(roomRequest.getRoomType().getType_name());
        room.setFloor_number(roomRequest.getFloor_number());
        room.setView(roomRequest.getView());
        room.setStatus(roomRequest.getStatus());
        room.setCleanlinessStatus(roomRequest.getCleanlinessStatus());
        return room;
    }


    public static RoomDetailsInfoDTO detailsMapToDTO(Room roomRequest){
        RoomDetailsInfoDTO details= new RoomDetailsInfoDTO();
        details.setId(roomRequest.getId());
        details.setRoom_number(roomRequest.getRoom_number());
        details.setFloor_number(roomRequest.getFloor_number());
        details.setStatus(roomRequest.getStatus());
        details.setView(roomRequest.getView());
        List<Room_Type_Feature> features= roomRequest.getRoomType().getRoom_type_feature();
        details.setFeatures( features.stream().map(feature -> Room_Type_FeatureMapper.mapToDTOFeature(feature)).collect(Collectors.toList()));
        List<Room_Type_Bed> beds= roomRequest.getRoomType().getRoom_type_bed();
        details.setBed_Type(beds.stream().map(bed-> Room_Type_BedMapper.mapToDTOBed(bed)).collect(Collectors.toList()));
        details.setPrice(roomRequest.getRoomType().getPrice());
        details.setType_name(roomRequest.getRoomType().getType_name());
        return details;
    }









    public static void update (Room room ,InsertRoomDTO roomRequest,Room_Type type) {
        room.setId(roomRequest.getId());
        room.setRoom_number(roomRequest.getRoom_number());
        room.setFloor_number(roomRequest.getFloor_number());
        room.setRoomType(type);
        room.setView(roomRequest.getView());
        room.setStatus(roomRequest.getStatus());

    }


    public static Room updateToAvailable (Room roomToupdate) {
    roomToupdate.setStatus(roomStatus.AVAILABLE);
      return roomToupdate;
    }
    public static Room ToEntity(InsertRoomDTO roomRequest, Room_Type room_type){
        System.out.println("eliana");
        System.out.println(roomRequest.getStatus());
        return Room.builder().room_number(roomRequest.getRoom_number())
                .status(roomRequest.getStatus())
                .view(roomRequest.getView())
                .floor_number(roomRequest.getFloor_number())
                .roomType(room_type)
                .cleanlinessStatus(roomRequest.getCleanlinessStatus())
                .build();

    }
    /*
    public static void update (Customer customerById , CustomerDto requestedCustomer) {
        customerById.setName(requestedCustomer.getName());
        customerById.setEmail(requestedCustomer.getEmail());
        customerById.setPhone_number(requestedCustomer.getPhone_number());

    }*/

}
