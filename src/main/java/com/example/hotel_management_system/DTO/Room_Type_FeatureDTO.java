package com.example.hotel_management_system.DTO;

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
