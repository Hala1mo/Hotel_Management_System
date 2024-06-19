package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Room.FeatureDTOv1;
import com.example.hotel_management_system.DTO.Room.FeatureDTOv2;
import com.example.hotel_management_system.DTO.Room.InsertFeatureDTO;
import com.example.hotel_management_system.Mapper.FeatureMapper;
import com.example.hotel_management_system.Models.Features;
import com.example.hotel_management_system.Repository.FeaturesRepository;
import com.example.hotel_management_system.Repository.RoomTypeRepository;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomFeaturesServiceImpl implements RoomFeaturesService {
   FeaturesRepository featuresRepository;
    RoomTypeRepository roomTypeRepository;



    @Autowired
    public RoomFeaturesServiceImpl(FeaturesRepository featuresRepository, RoomTypeRepository roomTypeRepository) {

        this.featuresRepository = featuresRepository;
        this.roomTypeRepository = roomTypeRepository;
    }
    public List<FeatureDTOv1> retrieveFeaturesV1(){
        List<Features> allFeatures= featuresRepository.findAll();
        if(allFeatures==null){
            throw new EntityNotFoundException("No features found in the database");
        }
        return allFeatures.stream().map(feature -> FeatureMapper.mapToDTOV1(feature)).collect(Collectors.toList());
    }

    public List<FeatureDTOv2> retrieveFeaturesV2(){
        List<Features> allFeatures= featuresRepository.findAll();
        if(allFeatures==null){
            throw new EntityNotFoundException("No features found in the database");
        }
        return allFeatures.stream().map(feature -> FeatureMapper.mapToDTOV2(feature)).collect(Collectors.toList());
    }


    public InsertFeatureDTO saveFeatures (InsertFeatureDTO request){
        Features featureToSave = FeatureMapper.ToEntity(request);
        featuresRepository.save(featureToSave);
        return  FeatureMapper.mapToDTO(featureToSave);
    }
    public ResponseEntity<?> updateFeatures (InsertFeatureDTO request, long id){
        Features featureToUpdate=featuresRepository.findAllById(id);
        FeatureMapper.update(featureToUpdate,request);
        featuresRepository.save(featureToUpdate);
        return ResponseEntity.ok("Updated Successfully");
    }
    public ResponseEntity<?> deleteFeature (long id){
        Features featureToDelete=featuresRepository.findAllById(id);
        featuresRepository.delete(featureToDelete);
        return ResponseEntity.ok("Successfully Deleted");
    }
}