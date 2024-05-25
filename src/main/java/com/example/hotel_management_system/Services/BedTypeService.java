package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Bed_TypeDTO;

import java.util.List;

public interface BedTypeService {
    Bed_TypeDTO saveBedType (Bed_TypeDTO request);
    List<Bed_TypeDTO> retrieveAllBedTypes();
}
