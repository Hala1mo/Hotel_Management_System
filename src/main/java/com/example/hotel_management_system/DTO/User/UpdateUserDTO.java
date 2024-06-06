package com.example.hotel_management_system.DTO.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UpdateUserDTO {

    @Valid
    @NotNull(message = "First Name is mandatory")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotNull(message = "Last Name is mandatory")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @NotNull(message = "Phone number is mandatory")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

}
