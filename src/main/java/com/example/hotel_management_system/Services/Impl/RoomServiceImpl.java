package com.example.hotel_management_system.Services.Impl;



import com.example.hotel_management_system.DTO.*;
import com.example.hotel_management_system.Mapper.Reserve_RoomMapper;
import com.example.hotel_management_system.Mapper.RoomMapper;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Models.Reserve_Room;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Models.Room_Type;
import com.example.hotel_management_system.Repository.RoomRepository;
import com.example.hotel_management_system.Repository.RoomTypeRepository;
import com.example.hotel_management_system.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
   RoomRepository roomRepository;
   RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository,RoomTypeRepository roomTypeRepository) {

        this.roomRepository=roomRepository;
        this.roomTypeRepository=roomTypeRepository;
    }
    @Override
    public List<RoomDTO> retrieveRooms() {
        List<Room> allRoom= roomRepository.findAll();
        return allRoom.stream().map(room -> RoomMapper.mapToDTO(room)).collect(Collectors.toList());
    }
    @Override
    public RoomDTO findRoomById(long id) {
        Room roomById = roomRepository.findAllById(id);
        return RoomMapper.mapToDTO(roomById);
    }
     public RoomDTO updateRoomById(long id,InsertRoomDTO requestedRoom) {
         Room_Type roomType=roomTypeRepository.findAllById(requestedRoom.getRoom_TypeID());
        Room roomById = roomRepository.findAllById(id);
        RoomMapper.update(roomById,requestedRoom,roomType);
        roomRepository.save(roomById);
        return RoomMapper.mapToDTO(roomById);
    }

    public ResponseEntity<?> saveNewRoom (InsertRoomDTO requestedRoom) {
        Room_Type roomType=roomTypeRepository.findAllById(requestedRoom.getRoom_TypeID());
        Room roomToSave = RoomMapper.ToEntity(requestedRoom,roomType);
       roomRepository.save(roomToSave);
    return ResponseEntity.ok("Saved Successfully");
    }
    public List<RoomDetailsInfoDTO>retrieveRoomsBySpecificStatus(roomStatus status){
        List<Room>rooms=roomRepository.findAllByStatus(status);

        return rooms.stream().map(room -> RoomMapper.detailsMapToDTO(room)).collect(Collectors.toList());
    }

    public List<RoomDetailsInfoDTO>retrieveRoomsBySpecificDates(Date checkIn,Date checkOut){
        List<Room>rooms=roomRepository.findAvailableRooms(checkIn,checkOut);
        return rooms.stream().map(room -> RoomMapper.detailsMapToDTO(room)).collect(Collectors.toList());
    }

    public List<RoomDTO>retrieveRoomsBySpecificView(roomView view){
        List<Room>rooms=roomRepository.findAllByView(view);
        return rooms.stream().map(room -> RoomMapper.mapToDTO(room)).collect(Collectors.toList());
    }


    public List<ReservationInfoDTO>retrieveReservationForSpecificRoom (long id ){
        Room room=roomRepository.findAllById(id);
        List<Reserve_Room> reseravtionForARoom=room.getBooking_room();

        return reseravtionForARoom.stream().map(reservation -> Reserve_RoomMapper.mapToBooking(reservation)).collect(Collectors.toList());
    }


}
