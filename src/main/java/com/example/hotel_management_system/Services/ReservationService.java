package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Reservation.ReservationDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    ResponseEntity<?> reserveBooking (ReservationDTO request);
    List<ReservationDTO> retrieveReservationForSpecificCustomer(Long id, String firstName);
    ResponseEntity<?> cancelAnReservation(long id);
    ResponseEntity<?> approveReservationCancellation(long id);
    // ResponseEntity<?> saveAdditionToSpecificBooking(List<Add_OnDTO> request, long id);
    ResponseEntity<?> modifyBooking(long id,ReservationDTO request);
    List<RoomDTO> retrieveRoomsForSpecficReservation(long id);

    ResponseEntity<?> confirmReservation( Long id);
    ResponseEntity<?> checkIn( Long id);
    ResponseEntity<?>  checkOut(long id);

    List<ReservationDTO>retrieveReservationsBySpecificDates(Date checkIn, Date checkOut);

}