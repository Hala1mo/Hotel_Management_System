package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Room_Type_BedDTO;
import com.example.hotel_management_system.Mapper.Room_Type_BedMapper;
import com.example.hotel_management_system.Models.Bed_Type;
import com.example.hotel_management_system.Models.Room_Type;
import com.example.hotel_management_system.Models.Room_Type_Bed;
import com.example.hotel_management_system.Repository.BedTypeRepository;
import com.example.hotel_management_system.Repository.ReservationRepository;
import com.example.hotel_management_system.Repository.RoomTypeRepository;
import com.example.hotel_management_system.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository=reservationRepository;
    }

/*    public ResponseEntity<?> addBedTypeForSpecificRoomType(Room_Type_BedDTO request){
        Room_Type room_type= roomTypeRepository.findAllById(request.getType_id());
        Bed_Type bed_type=roomBedTypeRepository.findAllById(request.getBed_id());
        int num_of_beds= request.getNum_beds();
        Room_Type_Bed room_type_bed= Room_Type_BedMapper.toEntity(room_type,bed_type,num_of_beds);
        room_type.getRoom_type_bed().add(room_type_bed);
        bed_type.getRoom_type_bed().add(room_type_bed);
        room_type_bedRepository.save(room_type_bed);
        roomTypeRepository.save(room_type);
        roomBedTypeRepository.save(bed_type);
        return ResponseEntity.ok("Saved Successfully");

    }*/
}
