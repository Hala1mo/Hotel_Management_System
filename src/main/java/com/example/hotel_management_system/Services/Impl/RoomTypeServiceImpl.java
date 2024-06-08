package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Room.*;
import com.example.hotel_management_system.Mapper.RoomTypeMapper;
import com.example.hotel_management_system.Mapper.Room_Type_BedMapper;
import com.example.hotel_management_system.Mapper.Room_Type_FeatureMapper;
import com.example.hotel_management_system.Models.*;
import com.example.hotel_management_system.Repository.*;
import com.example.hotel_management_system.Services.RoomTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    RoomTypeRepository roomTypeRepository;
    FeaturesRepository featureRepository;
    BedTypeRepository roomBedTypeRepository;
    Room_Type_FeatureRepository room_type_featuresRepository;
    Room_Type_BedRepository room_type_bedRepository;



    @Autowired
    public RoomTypeServiceImpl(FeaturesRepository featureRepository, RoomTypeRepository roomTypeRepository, Room_Type_FeatureRepository room_type_features, BedTypeRepository roomBedTypeRepository, Room_Type_BedRepository room_type_bedRepository) {
         this.room_type_featuresRepository=room_type_features;
         this.roomBedTypeRepository=roomBedTypeRepository;
        this.featureRepository=featureRepository;
        this.roomTypeRepository=roomTypeRepository;
        this.room_type_bedRepository=room_type_bedRepository;
    }

    public List<RoomTypeDTO> retrieveRoomsTypes(){
        List<Room_Type> allRoomTypes= roomTypeRepository.findAll();
        if(allRoomTypes==null){
            throw new EntityNotFoundException("No Room types found in database");
        }
        return allRoomTypes.stream().map(type -> RoomTypeMapper.mapToDTO(type)).collect(Collectors.toList());
    }

    public ResponseEntity<?> addFeatureForSpecificRoomType(Room_Type_FeatureDTO request){
       Room_Type room_type= roomTypeRepository.findAllById(request.getType_id());
        if(room_type==null){
            throw new EntityNotFoundException("No Room type found with this id");
        }
       Features features=featureRepository.findAllById(request.getFeature_id());
        if(features==null){
            throw new EntityNotFoundException("No feature found with this id");
        }
       Room_Type_Feature room_type_feature= Room_Type_FeatureMapper.toEntity(room_type,features);
       room_type.getRoom_type_feature().add(room_type_feature);
       features.getRoom_type_feature().add(room_type_feature);
       room_type_featuresRepository.save(room_type_feature);
       roomTypeRepository.save(room_type);
       featureRepository.save(features);
        return ResponseEntity.ok("Saved Successfully");

    }
    public ResponseEntity<?> addBedTypeForSpecificRoomType(Room_Type_BedDTO request){
        Room_Type room_type= roomTypeRepository.findAllById(request.getType_id());
        if(room_type==null){
            throw new EntityNotFoundException("No Room type found with this id");
        }
        Bed_Type bed_type=roomBedTypeRepository.findAllById(request.getBed_id());
        if(bed_type==null){
            throw new EntityNotFoundException("No Bed type found with this id");
        }
        int num_of_beds= request.getNum_beds();
        Room_Type_Bed room_type_bed= Room_Type_BedMapper.toEntity(room_type,bed_type,num_of_beds);
        room_type.getRoom_type_bed().add(room_type_bed);
        bed_type.getRoom_type_bed().add(room_type_bed);
        room_type_bedRepository.save(room_type_bed);
        roomTypeRepository.save(room_type);
        roomBedTypeRepository.save(bed_type);
        return ResponseEntity.ok("Saved Successfully");

    }
    public List<FeatureDTO> retrieveFeaturesForSpecificRoomType(long id){
        Room_Type room_type= roomTypeRepository.findAllById(id);
        if(room_type==null){
            throw new EntityNotFoundException("No Room type found with this id");
        }
        List<Room_Type_Feature> features=room_type.getRoom_type_feature();
        if(features==null){
            throw new EntityNotFoundException("No feature found for this room");
        }
        return features.stream().map(feature -> Room_Type_FeatureMapper.mapToDTOFeature(feature)).collect(Collectors.toList());
    }

    public List<Bed_TypeDTO> retrieveBedTypeForSpecificRoomType(Long id){
        Room_Type room_type= roomTypeRepository.findAllById(id);
        if(room_type==null){
            throw new EntityNotFoundException("No Room type found with this id");
        }
        List<Room_Type_Bed> beds=room_type.getRoom_type_bed();
        if(beds==null){
            throw new EntityNotFoundException("No beds found for this room");
        }
        return beds.stream().map(bed -> Room_Type_BedMapper.mapToDTOBed(bed)).collect(Collectors.toList());

    }
    public RoomTypeDTO saveRoomType (RoomTypeDTO request){
        Room_Type roomTypeToSave = RoomTypeMapper.ToEntity(request);
        roomTypeRepository.save(roomTypeToSave);
        return  RoomTypeMapper.mapToDTO(roomTypeToSave);

    }

}
