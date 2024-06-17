package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Room.*;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Services.RoomTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms/RoomType")
public class RoomTypeController {
    RoomTypeService roomTypeservice;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeservice) {
        this.roomTypeservice = roomTypeservice;
    }

    @GetMapping("")
    @Operation(summary = "Retrieve all room types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all room types",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomTypeDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No room types", content = @Content),
    })
    public List<RoomTypeDTO> retrieveRoomsTypes() {
        return roomTypeservice.retrieveRoomsTypes();
    }

    @GetMapping("Features/{id}")
    @Operation(summary = "Retrieve features for a specific room type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved features for a specific room type",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FeatureDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No features found for this room", content = @Content),
    })
    public List<FeatureDTO> retrieveFeaturesForSpecificRoomType(@PathVariable Long id) {
        return roomTypeservice.retrieveFeaturesForSpecificRoomType(id);
    }

    @GetMapping("/Bed/{id}")
    @Operation(summary = "Retrieve bed types for a specific room type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bed types for a specific room type",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Bed_TypeDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No bed types found for this room type", content = @Content),
    })
    public List<Bed_TypeDTO> retrieveBedTypeForSpecificRoomType(@PathVariable Long id) {
        return roomTypeservice.retrieveBedTypeForSpecificRoomType(id);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new room type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new room type",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomTypeDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })
    public RoomTypeDTO saveRoomType(@Valid @RequestBody RoomTypeDTO request) {
        return roomTypeservice.saveRoomType(request);
    }
    @PostMapping("/update")
    @Operation(summary = "Update a room type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Updated room type",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoomTypeDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })
    public RoomTypeDTO UpdateRoomType(@Valid @PathVariable long id,@Valid @RequestBody RoomTypeDTO request) {
        return roomTypeservice.updateRoomType(id,request);
    }


    @PostMapping("/save/Features")
    @Operation(summary = "Add a feature for a specific room type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added a feature for a specific room type",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room_Type_FeatureDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
    })
    public ResponseEntity<?> addFeatureForSpecificRoomType(@Valid @RequestBody Room_Type_FeatureDTO request) {
        return roomTypeservice.addFeatureForSpecificRoomType(request);
    }

    @PostMapping("/save/Bed")
    @Operation(summary = "Add a bed type for a specific room type")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully added a bed type for a specific room type",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = Room_Type_BedDTO.class))),
    @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
})
    public ResponseEntity<?> addBedTypeForSpecificRoomType (@Valid @RequestBody Room_Type_BedDTO request){
        return roomTypeservice.addBedTypeForSpecificRoomType(request);
    }
}
