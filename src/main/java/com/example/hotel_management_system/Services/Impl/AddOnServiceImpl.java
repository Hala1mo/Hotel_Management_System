package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Add_OnDTO;
import com.example.hotel_management_system.Mapper.Add_OnMapper;
import com.example.hotel_management_system.Models.Add_On;
import com.example.hotel_management_system.Repository.Add_OnRepository;
import com.example.hotel_management_system.Services.AddOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddOnServiceImpl implements AddOnService {
    Add_OnRepository addOnRepository;

    @Autowired
    public AddOnServiceImpl(  Add_OnRepository addOnRepository) {
   this.addOnRepository=addOnRepository;
    }

    public  Add_OnDTO saveAddOn (Add_OnDTO request){
        Add_On Add_On = Add_OnMapper.ToEntity(request);
        addOnRepository.save(Add_On);
        return Add_OnMapper.mapToDTO(Add_On);
    }
   public  Add_OnDTO updateAddOn (Add_OnDTO request,long id){
        Add_On add=addOnRepository.findAllById(id);
        add= Add_OnMapper.update(add, request);
        addOnRepository.save(add);
        return Add_OnMapper.mapToDTO(add);
    }
    public List<Add_OnDTO> retrieveAddOnFeatures(){
        List<Add_On> addOnFeatures  =addOnRepository.findAll();
        return addOnFeatures.stream().map(addon -> Add_OnMapper.mapToDTO(addon)).collect(Collectors.toList());
    }

}
