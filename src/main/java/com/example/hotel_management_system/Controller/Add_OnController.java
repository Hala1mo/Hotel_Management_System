package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Reservation.Add_OnDTO;
import com.example.hotel_management_system.Services.AddOnService;
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
@RequestMapping("api/reservations/addOn")
public class Add_OnController {
    AddOnService addOnService;
    @Autowired
    public Add_OnController(AddOnService addOnService){
        this.addOnService=addOnService;
    }

    @GetMapping("")
    @Operation(summary = "Retrieve all add-ons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of add-ons",
                    content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = Add_OnDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
    })
    @PreAuthorize("isAuthenticated()")
    public List<Add_OnDTO> retrieveAddOn(){
        return addOnService.retrieveAddOnFeatures();
    }

    @PostMapping("/create")
    @Operation(summary = "Save a new add-on")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully saved add-on",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = Add_OnDTO.class))),
    @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
})
    @PreAuthorize("hasRole('ADMIN')")
    public Add_OnDTO saveAddOn (@Valid @RequestBody Add_OnDTO request){
        return addOnService.saveAddOn(request);
    }

    @PutMapping("{id}/update")
    @Operation(summary = "Update an existing add-on")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully updated add-on",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = Add_OnDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    public Add_OnDTO updateAddOn (@Valid @RequestBody Add_OnDTO request, @PathVariable long id){
        return addOnService.updateAddOn(request, id);
    }
}
