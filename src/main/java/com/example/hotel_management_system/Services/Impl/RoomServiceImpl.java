package com.example.hotel_management_system.Services.Impl;


import com.example.hotel_management_system.DTO.Room.InsertRoomDTO;
import com.example.hotel_management_system.DTO.Reservation.ReservationInfoDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsInfoDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsNotSpecifiedDTO;
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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;
    RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomTypeRepository roomTypeRepository) {
        this.roomRepository=roomRepository;
        this.roomTypeRepository=roomTypeRepository;
    }

    public List<RoomDTO> retrieveRooms() {
        List<Room> allRoom= roomRepository.findAll();
        if (allRoom.isEmpty()) {
            throw new EntityNotFoundException("No Rooms found in the database.");
        }
        return allRoom.stream().map(room -> RoomMapper.mapToDTO(room)).collect(Collectors.toList());
    }

    public RoomDTO findRoomById(long id) {
        Room roomById = roomRepository.findAllById(id);
        if (roomById==null) {
            throw new EntityNotFoundException("No Room found with this id");
        }
        return RoomMapper.mapToDTO(roomById);
    }

    public RoomDetailsNotSpecifiedDTO findRoomByIdForUser(long id) {
        Room roomById = roomRepository.findAllById(id);
        if (roomById==null) {
            throw new EntityNotFoundException("No Room found with this id");
        }
        return RoomMapper.mapToDTOButNotSpecified(roomById);
    }
    public RoomDTO updateRoomById(long id,InsertRoomDTO requestedRoom) {
        Room_Type roomType=roomTypeRepository.findAllById(requestedRoom.getRoom_TypeID());

        if(roomType==null){
            throw new EntityNotFoundException("Room  type not found with id: " + id);
        }
        Room roomById = roomRepository.findAllById(id);

        if(roomById==null){
            throw new EntityNotFoundException("Room not found with id: " + id);
        }
        RoomMapper.update(roomById,requestedRoom,roomType);
        roomRepository.save(roomById);
        return RoomMapper.mapToDTO(roomById);
    }

    public ResponseEntity<?> saveNewRoom (InsertRoomDTO requestedRoom) {
        Room_Type roomType=roomTypeRepository.findAllById(requestedRoom.getRoom_TypeID());
        if (roomType==null) {
            throw new EntityNotFoundException("No Room type with this id");
        }
        Room roomToSave = RoomMapper.ToEntity(requestedRoom,roomType);
        roomRepository.save(roomToSave);
        return ResponseEntity.ok("Saved Successfully");
    }
    public List<RoomDetailsInfoDTO>retrieveRoomsBySpecificStatus(roomStatus status){
        List<Room>rooms=roomRepository.findAllByStatus(status);
        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("No Rooms found for this status:"+status);
        }
        return rooms.stream().map(room -> RoomMapper.detailsMapToDTO(room)).collect(Collectors.toList());
    }

    public List<RoomDetailsInfoDTO>retrieveRoomsBySpecificDates(Date checkIn,Date checkOut){
        List<Room>rooms=roomRepository.findAvailableRooms(checkIn,checkOut);

        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("No Rooms available in these dates.");
        }

        return rooms.stream().map(room -> RoomMapper.detailsMapToDTO(room)).collect(Collectors.toList());
    }

    public List<RoomDTO>retrieveRoomsBySpecificView(roomView view){
        List<Room>rooms=roomRepository.findAllByView(view);
        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("No Rooms found in this view");
        }
        return rooms.stream().map(room -> RoomMapper.mapToDTO(room)).collect(Collectors.toList());
    }


    @Override
    public List<RoomDTO> retrieveAvailableRoomsBySpecificView(roomView view) {
        List<Room> rooms = roomRepository.findAllByView(view);
        System.out.println(rooms);
        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("No Rooms found with view: " + view);
        }
        return rooms.stream()
                .filter(room -> room.getStatus().equals(roomStatus.AVAILABLE))
                .map(room -> RoomMapper.mapToDTO(room))
                .collect(Collectors.toList());
    }


    public List<RoomDetailsNotSpecifiedDTO> retrieveAvailableRoomsBySpecificViewNotSpecified(roomView view) {
        List<Room> rooms = roomRepository.findAllByView(view);
        System.out.println(rooms);
        if (rooms.isEmpty()) {
            throw new EntityNotFoundException("No Rooms found with view: " + view);
        }
        return rooms.stream()
                .filter(room -> room.getStatus().equals(roomStatus.AVAILABLE))
                .map(room -> RoomMapper.mapToDTOButNotSpecified(room))
                .collect(Collectors.toList());
    }

    public List<ReservationInfoDTO>retrieveReservationForSpecificRoom (long id ){
        Room room=roomRepository.findAllById(id);
        if(room==null){
            throw new EntityNotFoundException("No Room found with this id");
        }
        List<Reserve_Room> reseravtionForARoom=room.getBooking_room();

        if(reseravtionForARoom==null){
            throw new EntityNotFoundException("No Reservations found for this room with id"+id);
        }

        return reseravtionForARoom.stream().map(reservation -> Reserve_RoomMapper.mapToBooking(reservation)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getRoomsByCleanStatus(String status) {
        List<Room> queryResult = roomRepository.findAll();
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("No rooms found by this status:"+status);
        }
        List<RoomDTO> rooms = new ArrayList<>();
        for (Room room : queryResult) {
            if(room.getCleanlinessStatus().toString().equals(status)) {
                RoomDTO employeeDTO = RoomMapper.mapToDTO(room);
                rooms.add(employeeDTO);
            }
        }
        return rooms;
    }
}
