package com.example.hotel_management_system.DTO.Room;

import com.example.hotel_management_system.Models.Enum.CleanlinessStatus;
import com.example.hotel_management_system.Models.Enum.Department;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private long id;

    @NotNull
    @NotBlank
    private int room_number;

    @NotNull
    @NotBlank
    private int floor_number;

    @Enumerated
    @NotNull
    private roomStatus status ;

    @Enumerated
    @NotNull
    private roomView view ;

    @NotNull
    @NotBlank
    private String room_Type;

    @NotNull
    @Enumerated
    private CleanlinessStatus cleanlinessStatus;
}
