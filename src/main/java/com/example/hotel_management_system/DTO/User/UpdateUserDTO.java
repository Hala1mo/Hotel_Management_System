package com.example.hotel_management_system.DTO.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;


@Data
public class UpdateUserDTO {


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

}
