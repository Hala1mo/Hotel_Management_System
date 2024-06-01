package com.example.hotel_management_system.DTO.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class LoginDTO {
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    public LoginDTO(String email, String password) {
        this.email=email;
        this.password=password;
    }
}