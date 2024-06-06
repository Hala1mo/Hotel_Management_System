package com.example.hotel_management_system.DTO.Room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room_Type_BedDTO {
    private long id;


    @Valid
    @NotNull(message = "Room type id is mandatory")
    private long type_id;

    @NotNull(message = "Bed id is mandatory")
    private long bed_id;

    @NotNull(message = "Number of beds is mandatory")
    private int num_beds;
}
