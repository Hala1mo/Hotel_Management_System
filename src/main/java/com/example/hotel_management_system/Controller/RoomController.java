package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.*;
import com.example.hotel_management_system.DTO.Room.InsertRoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsInfoDTO;
import com.example.hotel_management_system.DTO.Room.RoomDetailsNotSpecifiedDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Services.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    // get all the rooms in specific floor
    RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bedTypes")
    @Operation(summary = "Get all Bed types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all bed types",
                    content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No  bed types available", content = @Content),
    })
    public List<RoomDTO> retrieveBedTypes(){
        return roomService.retrieveRooms();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/status/{status}")
    @Operation(summary = "Get rooms by specific status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved rooms for specific status",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDetailsInfoDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms found with this status", content = @Content),
    })
    public List<RoomDetailsInfoDTO> retrieveRoomsBySpecificStatus(@PathVariable roomStatus status){
        return roomService.retrieveRoomsBySpecificStatus(status);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/view/{view}")
    @Operation(summary = "Get rooms for specific view")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved rooms for specific view",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms found for this view", content = @Content),
    })
    public List<RoomDTO>retrieveRoomsBySpecificView(@PathVariable roomView view){
        return roomService.retrieveRoomsBySpecificView(view);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/available/{view}")
    @Operation(summary = "Get available rooms for specific view for admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved available rooms for specific view",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms available for this view", content = @Content),
    })
    public List<RoomDTO>retrieveRoomsAvailableBySpecificView(@PathVariable roomView view){
        return roomService.retrieveAvailableRoomsBySpecificView(view);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/available/view/{view}")
    @Operation(summary = "Get available rooms for specific view for users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved available rooms for specific view",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms available for this view", content = @Content),
    })
    public List<RoomDetailsNotSpecifiedDTO>retrieveAvailableRoomsBySpecificViewNotSpecified(@PathVariable roomView view){
        return roomService.retrieveAvailableRoomsBySpecificViewNotSpecified(view);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/available")
    @Operation(summary = "Get available rooms by check-in and check-out dates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved available rooms",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDetailsInfoDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms found in this period of time", content = @Content),
    })
    public List<RoomDetailsInfoDTO> getAvailableRooms(
            @RequestParam("check-in-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @RequestParam("check-out-date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate) {
        List<RoomDetailsInfoDTO> availableRooms = roomService.retrieveRoomsBySpecificDates(checkInDate,checkOutDate);
        return availableRooms;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/reservations/{id}")
    @Operation(summary = "Get reservations for a specific room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reservations for a specific room",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ReservationInfoDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No reservations found with id", content = @Content),
    })
    public List<ReservationInfoDTO> retrieveReservationForSpecificRoom (@PathVariable long id ) {
        return roomService.retrieveReservationForSpecificRoom(id);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all rooms",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms found", content = @Content),
    })
    public List<RoomDTO> getAllRooms() {
        return roomService.retrieveRooms();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @Operation(summary = "Create a new room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new room",
                    content = @Content(mediaType = "application/json",schema =  @Schema(implementation = InsertRoomDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })

    public ResponseEntity<?> saveNewRoom (@Valid @RequestBody InsertRoomDTO requestedRoom){
        return roomService.saveNewRoom(requestedRoom);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get a room by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a room by ID",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RoomDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found with this id", content = @Content)
    })
    public RoomDTO getRoomById(@PathVariable Long id) {
        return roomService.findRoomById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/id/{id}")
    @Operation(summary = "Get a room by ID for Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a room by ID",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RoomDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found with this id", content = @Content)
    })
    public RoomDetailsNotSpecifiedDTO getRoomByIdForUser(@PathVariable Long id) {
        return roomService.findRoomByIdForUser(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("update/id/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a room by ID",
                    content = @Content(mediaType = "application/json",schema = @Schema(implementation = RoomDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found with this id", content = @Content)
    })
    public RoomDTO updateRoom(@PathVariable Long id,@Valid @RequestBody InsertRoomDTO requestedRoom) {
        return roomService.updateRoomById(id,requestedRoom);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cleanliness/{status}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all rooms with this status",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No rooms found with this status", content = @Content),
    })
    public List<RoomDTO> getRoomByCleanStatus(@PathVariable String status) {
        return roomService.getRoomsByCleanStatus(status);
    }



}
