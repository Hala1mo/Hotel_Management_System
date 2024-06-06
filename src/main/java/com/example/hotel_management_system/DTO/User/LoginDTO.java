package com.example.hotel_management_system.DTO.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class LoginDTO {
    @Valid
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public LoginDTO(String email, String password) {
        this.email=email;
        this.password=password;
    }
}