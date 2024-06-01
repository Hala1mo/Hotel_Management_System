package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Repository.ReservationRepository;
import com.example.hotel_management_system.Services.ReservationService;
import com.example.hotel_management_system.Services.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Reservations")
public class ReservationController {
    RoomTypeService roomTypeService;
    ReservationService reservationService;

    @Autowired
    public ReservationController(RoomTypeService roomTypeservice,ReservationService reservationService) {
        this.reservationService=reservationService;
        this.roomTypeService = roomTypeservice;
    }
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
    @GetMapping("/{id}/rooms")
    public List<RoomDTO> retrieveRoomsForSpecificBook(@PathVariable long id ){
        return reservationService.retrieveRoomsForSpecficReservation(id);
    }
    @GetMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAnReservation(@PathVariable long id ){
        return reservationService.cancelAnReservation(id);
    }

    @GetMapping("/approvecancellation/{id}")
    public ResponseEntity<?> approveCancellation(@PathVariable long id ){
        return reservationService.approveReservationCancellation(id);
    }

    @PostMapping("/Book")
    public ResponseEntity<?> reserveBooking(@RequestBody ReservationDTO request){
        return reservationService.reserveBooking(request);
    }

    @PostMapping("/Book/{id}/addOn")
    public ResponseEntity<?> saveAdditionToSpecificBooking(@PathVariable long id, @RequestBody List<Add_OnDTO> request){
        return reservationService.saveAdditionToSpecificBooking(request,id);
    }

    @PutMapping("/Book/{id}/update")
    public ResponseEntity<?> updateSpecificBooking(@PathVariable long id, @RequestBody ReservationDTO request){
        return reservationService.modifyBooking(id,request);
    }







}