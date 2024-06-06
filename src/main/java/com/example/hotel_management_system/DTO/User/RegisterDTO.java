package com.example.hotel_management_system.DTO.User;

import com.example.hotel_management_system.Models.Enum.Role;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {
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
    @NotNull(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotNull(message = "Role is mandatory")
    @Enumerated
    private Role role;
}
