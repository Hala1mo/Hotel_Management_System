package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.Reservation.Add_OnDTO;
import com.example.hotel_management_system.Models.Add_On;

public class Add_OnMapper {
    public static Add_OnDTO mapToDTO(Add_On request){
        Add_OnDTO Add_On=new Add_OnDTO();
        Add_On.setAdd_on_name(request.getAdd_on_name());
        Add_On.setId(request.getId());
        Add_On.setPrice(request.getPrice());
        return Add_On;
    }

    public static Add_On update(Add_On add_onToUpdate , Add_OnDTO add_on) {
        add_onToUpdate.setAdd_on_name(add_on.getAdd_on_name());
        add_onToUpdate.setId(add_on.getId());
        add_onToUpdate.setPrice(add_on.getPrice());
        return  add_onToUpdate;
    }
    public static Add_On ToEntity(Add_OnDTO request){
        return  Add_On.builder()
                .add_on_name(request.getAdd_on_name())
                .price(request.getPrice())
                .build();
    }
}
