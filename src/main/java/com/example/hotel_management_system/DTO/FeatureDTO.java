package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Room_Type_Feature;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDTO {

    private long id;

    @NotNull
    @NotBlank
    @Size(min=3,max = 50)
    private String feature;

    @NotNull
    @NotBlank
    @Size(min=3,max = 50)
    private String description;

}
