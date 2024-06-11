package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Room.Bed_TypeDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Services.BedTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/rooms/BedType")
public class RoomBedTypeController {
    BedTypeService bedTypeService;
    @Autowired
    public RoomBedTypeController(BedTypeService bedTypeService){
        this.bedTypeService=bedTypeService;
    }

    @GetMapping("")
    @Operation(summary = "Retrieve all bed types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description  = "Successfully retrieved list of bed types",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Bed_TypeDTO.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No bed types found", content = @Content),
    })
    public List<Bed_TypeDTO> retrieveBedTypes(){
        return bedTypeService.retrieveAllBedTypes();
    }

    @PostMapping("/create")
    @Operation(summary = "Save a new bed type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully saved bed type",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bed_TypeDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Bed_TypeDTO saveBedType(@Valid @RequestBody Bed_TypeDTO request){
        return bedTypeService.saveBedType(request);
    }
}
