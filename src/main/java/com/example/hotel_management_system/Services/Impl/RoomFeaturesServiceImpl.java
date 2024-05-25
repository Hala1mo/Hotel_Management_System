package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.FeatureDTO;
import com.example.hotel_management_system.DTO.RoomTypeDTO;
import com.example.hotel_management_system.Mapper.FeatureMapper;
import com.example.hotel_management_system.Mapper.RoomTypeMapper;
import com.example.hotel_management_system.Models.Features;
import com.example.hotel_management_system.Models.Room_Type;
import com.example.hotel_management_system.Repository.FeaturesRepository;
import com.example.hotel_management_system.Repository.RoomRepository;
import com.example.hotel_management_system.Repository.RoomTypeRepository;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import com.example.hotel_management_system.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomFeaturesServiceImpl implements RoomFeaturesService {
   FeaturesRepository featuresRepository;
    RoomTypeRepository roomTypeRepository;



    @Autowired
    public RoomFeaturesServiceImpl( FeaturesRepository featuresRepository, RoomTypeRepository roomTypeRepository) {

        this.featuresRepository = featuresRepository;
        this.roomTypeRepository = roomTypeRepository;
    }
    public List<FeatureDTO> retrieveFeatures(){
        List<Features> allFeatures= featuresRepository.findAll();
        return allFeatures.stream().map(feature -> FeatureMapper.mapToDTO(feature)).collect(Collectors.toList());
    }

    public FeatureDTO saveFeatures (FeatureDTO request){
        Features featureToSave = FeatureMapper.ToEntity(request);
        featuresRepository.save(featureToSave);
        return  FeatureMapper.mapToDTO(featureToSave);
    }




}