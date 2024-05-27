package com.example.hotel_management_system.DTO;

import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

public class RoomDTO {
    @NotNull
    @NotBlank
    private String ID;
}
