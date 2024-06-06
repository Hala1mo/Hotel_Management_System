package com.example.hotel_management_system.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Add_OnDTO {

    private long id;

   @Valid
   @NotBlank(message = "Add on name is mandatory")
    @Size(min=3,max = 50)
    private String add_on_name;
    @NotNull(message = "Price for the addition is mandatory")
    private double price;

}