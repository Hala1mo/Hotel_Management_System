package com.example.hotel_management_system.DTO.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordChangeDTO {

        @Valid
        @NotNull(message = "new Password is mandatory")
        @NotBlank(message = "new Password is mandatory")
        private String newPassword;

}
