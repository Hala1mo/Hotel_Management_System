package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Features;
import com.example.hotel_management_system.Models.Room_Type;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room_Type_FeatureDTO {

    private long id;

    @NotNull
    private long type_id;


    @NotNull
    private long feature_id;

}
