package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.Services.ReservationService;
import com.example.hotel_management_system.Services.RoomTypeService;
import jakarta.validation.Valid;
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
    @PostMapping("/Book")
    public ResponseEntity<?> reserveBooking(@RequestBody ReservationDTO request){
        return reservationService.reserveBooking(request);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/Book/{id}/addOn")
    public ResponseEntity<?> saveAdditionToSpecificBooking(@PathVariable long id, @RequestBody List<Add_OnDTO> request){
        return reservationService.saveAdditionToSpecificBooking(request,id);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/Book/{id}/update")
    public ResponseEntity<?> updateSpecificBooking(@PathVariable long id, @RequestBody ReservationDTO request){
        return reservationService.modifyBooking(id,request);
    }







}