package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Enum.CleanlinessStatus;
import com.example.hotel_management_system.Models.Enum.Department;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class RoomDTO {

    private Long ID;
    @NotNull
    @Enumerated
    private CleanlinessStatus cleanlinessStatus;
}
