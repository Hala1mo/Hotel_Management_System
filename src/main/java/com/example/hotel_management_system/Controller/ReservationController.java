package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.ReservationDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsInfoDTO;
import com.example.hotel_management_system.Services.ReservationService;
import com.example.hotel_management_system.Services.RoomTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    RoomTypeService roomTypeService;
    ReservationService reservationService;

    @Autowired
    public ReservationController(RoomTypeService roomTypeservice, ReservationService reservationService) {
        this.reservationService=reservationService;
        this.roomTypeService = roomTypeservice;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    @Operation(summary = "Find reservations for a customer")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved reservations",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ReservationDTO.class))))
    public  List<ReservationDTO> findReservationForCustomer(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            return reservationService.retrieveReservationForSpecificCustomer(id, null);
        } else if (name != null) {
            return reservationService.retrieveReservationForSpecificCustomer(null, name);
        }
        throw new EntityNotFoundException("User Not Found");
    }

    @GetMapping("/{id}/rooms")
    @Operation(summary = "Retrieve rooms for a specific booking")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved rooms",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class))))
    @PreAuthorize("isAuthenticated()")
    public List<RoomDTO> retrieveRoomsForSpecificBook(@PathVariable long id ){
        return reservationService.retrieveRoomsForSpecficReservation(id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cancel/{id}")
    @Operation(summary = "Cancel a reservation")
    @ApiResponse(responseCode = "200", description = "Reservation canceled successfully")
    public ResponseEntity<?> cancelAnReservation(@PathVariable long id ){
        return reservationService.cancelAnReservation(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/approvecancellation/{id}")
    @Operation(summary = "Approve reservation cancellation")
    @ApiResponse(responseCode = "200", description = "Reservation cancellation approved")
    public ResponseEntity<?> approveCancellation(@PathVariable long id ){
        return reservationService.approveReservationCancellation(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/Book/create")
    @Operation(summary = "Reserve a booking")
    @ApiResponse(responseCode = "200", description = "Booking reserved successfully")
    public ResponseEntity<?> reserveBooking(@RequestBody ReservationDTO request){
        return reservationService.reserveBooking(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/confirm")
    @Operation(summary = "Confirm a reservation")
    @ApiResponse(responseCode = "200", description = "Reservation confirmed successfully")
    public ResponseEntity<?> confirmReservation(@PathVariable Long id) {
        return  reservationService.confirmReservation(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Check-in for a reservation")
    @ApiResponse(responseCode = "200", description = "Check-in successful")
    @PostMapping("/{id}/checkin")
    public ResponseEntity<?> checkIn(@PathVariable Long id) {
        return  reservationService.checkIn(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/checkout")
    @Operation(summary = "Check-out for a reservation")
    @ApiResponse(responseCode = "200", description = "Check-out successful")
    public ResponseEntity<?> checkOut(@PathVariable Long id) {
        return  reservationService.checkOut(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/Book/{id}/update")
    @Operation(summary = "Update a specific booking")
    @ApiResponse(responseCode = "200", description = "Booking updated successfully")
    public ResponseEntity<?> updateSpecificBooking(@PathVariable long id, @RequestBody ReservationDTO request){
        return reservationService.modifyBooking(id,request);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reserved/period")
    @Operation(summary = "Get Reservations by check-in and check-out dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reservations",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDetailsInfoDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No reservations found in this period of time", content = @Content),
    })
    public List<ReservationDTO> getReservations(
            @RequestParam("check-in-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @RequestParam("check-out-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate) {
        return reservationService.retrieveReservationsBySpecificDates(checkInDate,checkOutDate);



    }

}