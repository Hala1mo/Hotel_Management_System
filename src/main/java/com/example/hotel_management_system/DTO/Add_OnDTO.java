package com.example.hotel_management_system.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_OnDTO {

    private long id;

    @NotNull
    @NotBlank
    @Size(min=3,max = 50)
    private String add_on_name;

    @NotNull
    private double price;

}