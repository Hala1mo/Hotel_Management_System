package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.Services.AddOnService;
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
    public Add_OnController( AddOnService addOnService){
        this.addOnService=addOnService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public List<Add_OnDTO > retrieveAddOn(){
        return addOnService.retrieveAddOnFeatures();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Add_OnDTO saveAddOn (@Valid @RequestBody  Add_OnDTO request){
        return addOnService.saveAddOn(request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}/update")
    public Add_OnDTO updateAddOn (Add_OnDTO request,long id){
        return addOnService.updateAddOn(request, id);
    }
}
