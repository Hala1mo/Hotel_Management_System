package com.example.hotel_management_system.Services.Impl;



import com.example.hotel_management_system.DTO.Bed_TypeDTO;
import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Mapper.Bed_TypeMapper;
import com.example.hotel_management_system.Mapper.FeatureMapper;
import com.example.hotel_management_system.Mapper.RoomMapper;
import com.example.hotel_management_system.Mapper.RoomTypeMapper;
import com.example.hotel_management_system.Models.*;
import com.example.hotel_management_system.Repository.BedTypeRepository;
import com.example.hotel_management_system.Repository.FeaturesRepository;
import com.example.hotel_management_system.Repository.RoomRepository;
import com.example.hotel_management_system.Repository.RoomTypeRepository;
import com.example.hotel_management_system.Services.BedTypeService;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import com.example.hotel_management_system.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BedTypeServiceImpl implements BedTypeService {
    BedTypeRepository bedTypeRepository;
    RoomTypeRepository roomTypeRepository;

    @Autowired
    public BedTypeServiceImpl(BedTypeRepository bedTypeRepository, RoomTypeRepository roomTypeRepository) {
        this.bedTypeRepository=bedTypeRepository;
        this.roomTypeRepository = roomTypeRepository;

    }
    public List<Bed_TypeDTO> retrieveAllBedTypes(){
        List<Bed_Type> allBedTypes= bedTypeRepository.findAll();
        return allBedTypes.stream().map(bed -> Bed_TypeMapper.mapToDTO(bed)).collect(Collectors.toList());
    }

    public Bed_TypeDTO saveBedType (Bed_TypeDTO request){
       Bed_Type bedTypeToSave =Bed_TypeMapper.ToEntity(request);
        bedTypeRepository.save(bedTypeToSave);
        return  Bed_TypeMapper.mapToDTO(bedTypeToSave);
    }




}