package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReservationService {
    ResponseEntity<?> reserveBooking (ReservationDTO request);
    ResponseEntity<?> retrieveReservationForSpecificCustomer(Long id, String firstName);
    ResponseEntity<?> cancelAnReservation(long id);
    ResponseEntity<?> approveReservationCancellation(long id);
   // ResponseEntity<?> saveAdditionToSpecificBooking(List<Add_OnDTO> request, long id);
    ResponseEntity<?> modifyBooking(long id,ReservationDTO request);
    List<RoomDTO> retrieveRoomsForSpecficReservation(long id);

    ResponseEntity<?> confirmReservation( Long id);
    ResponseEntity<?> checkIn( Long id);
    ResponseEntity<?>  checkOut(long id);

}
