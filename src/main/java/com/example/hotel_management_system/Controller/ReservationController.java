package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.Services.ReservationService;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    RoomTypeService roomTypeService;
    ReservationService reservationService;

    @Autowired
    public ReservationController(RoomTypeService roomTypeservice,ReservationService reservationService) {
        this.reservationService=reservationService;
        this.roomTypeService = roomTypeservice;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<? >findReservationForCustomer(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(reservationService.retrieveReservationForSpecificCustomer(id, null));
        } else if (name != null) {
            return ResponseEntity.ok(reservationService.retrieveReservationForSpecificCustomer(null, name));
        } else {
            return ResponseEntity.ok("USER NOT FOUND");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}/rooms")
    public List<RoomDTO> retrieveRoomsForSpecificBook(@PathVariable long id ){
        return reservationService.retrieveRoomsForSpecficReservation(id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAnReservation(@PathVariable long id ){
        return reservationService.cancelAnReservation(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approvecancellation/{id}")
    public ResponseEntity<?> approveCancellation(@PathVariable long id ){
        return reservationService.approveReservationCancellation(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/Book/create")
    public ResponseEntity<?> reserveBooking(@RequestBody ReservationDTO request){
        return reservationService.reserveBooking(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<?> confirmReservation(@PathVariable Long id) {
      return  reservationService.confirmReservation(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/checkin")
    public ResponseEntity<?> checkIn(@PathVariable Long id) {
     return  reservationService.checkIn(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/checkout")
    public ResponseEntity<?> checkOut(@PathVariable Long id) {
      return  reservationService.checkOut(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/Book/{id}/update")
    public ResponseEntity<?> updateSpecificBooking(@PathVariable long id, @RequestBody ReservationDTO request){
        return reservationService.modifyBooking(id,request);
    }







}