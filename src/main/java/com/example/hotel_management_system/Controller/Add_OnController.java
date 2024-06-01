package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.Services.AddOnService;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/addOn")
public class Add_OnController {
    AddOnService addOnService;
    @Autowired
    public Add_OnController( AddOnService addOnService){

        this.addOnService=addOnService;
    }

    @GetMapping("")
    public List<Add_OnDTO > retrieveAddOn(){
        return addOnService.retrieveAddOnFeatures();
    }

    @PostMapping("")
    public Add_OnDTO saveAddOn (@RequestBody  Add_OnDTO request){
        return addOnService.saveAddOn(request);
    }
    @PutMapping("{id}/update")
    public Add_OnDTO updateAddOn (Add_OnDTO request,long id){
        return addOnService.updateAddOn(request, id);
    }
}
