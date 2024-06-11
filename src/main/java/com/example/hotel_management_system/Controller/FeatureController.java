package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Room.FeatureDTO;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/rooms/features")
public class FeatureController {
    RoomFeaturesService roomFeaturesService;

    @Autowired
    public FeatureController(RoomFeaturesService roomFeaturesService){
        this.roomFeaturesService=roomFeaturesService;
    }

    @GetMapping("")
    @Operation(summary = "Retrieve all room features")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of room features",
                    content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = FeatureDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })
    @PreAuthorize("isAuthenticated()")
    public List<FeatureDTO> retrieveFeatures(){
        return roomFeaturesService.retrieveFeatures();
    }

    @PostMapping("/create")
    @Operation(summary = "Save a new room feature")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Successfully saved room feature",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = FeatureDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })
    @PreAuthorize("hasRole('ADMIN')")
    public FeatureDTO saveFeatures(@Valid @RequestBody FeatureDTO request){
        return roomFeaturesService.saveFeatures(request);
    }

    @PostMapping("/update")
    @Operation(summary = "Update an existing room feature")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully updated room feature",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = FeatureDTO.class))),
    @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateFeatures(@Valid @RequestBody FeatureDTO request, @RequestParam long id){
        return roomFeaturesService.updateFeatures(request, id);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a room feature")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted room feature"),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteFeatures(@RequestParam long id){
        return roomFeaturesService.deleteFeature(id);
    }
}
