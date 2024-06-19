package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Reservation.Add_OnDTO;

import java.util.List;

public interface AddOnService {
    Add_OnDTO saveAddOn (Add_OnDTO request);
    List<Add_OnDTO > retrieveAddOnFeatures();
    Add_OnDTO updateAddOn (Add_OnDTO request,long id);
}
