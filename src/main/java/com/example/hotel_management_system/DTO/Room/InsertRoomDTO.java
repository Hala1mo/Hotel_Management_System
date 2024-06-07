package com.example.hotel_management_system.DTO.Room;

import com.example.hotel_management_system.Models.Enum.CleanlinessStatus;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertRoomDTO {
    private long id;

    @Valid

    @NotNull(message = "Room Number is mandatory")
    private Integer room_number;

    @NotNull(message = "Floor Number is mandatory")
    private Integer floor_number;

    @NotNull(message = "Status is mandatory")
    @Enumerated
    private roomStatus status;

    @NotNull(message = "View is mandatory")
    @Enumerated
    private roomView view;

    @NotNull(message = "Room type id is mandatory")
    private Long room_TypeID;

    @NotNull(message = "CleanlinessStatus is mandatory")
    @Enumerated
    private CleanlinessStatus cleanlinessStatus;
}
