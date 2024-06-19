package com.example.hotel_management_system.DTO.Room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FeatureDTOv2 {


    private long id;

    @Valid
    @NotBlank(message = "Feature is mandatory")
    private String feature;

    @NotBlank(message = "Description is mandatory")
    private String description;

}
