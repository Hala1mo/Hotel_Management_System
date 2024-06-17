package com.example.hotel_management_system.DTO.Room;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO {

    private long id;

    @Valid
    @NotNull(message = "Price for the room is mandatory")
    private Double price;

    @NotBlank(message = "Room type is mandatory")
    private String type_name;

    @NotNull(message = "Size of room is mandatory")
    private Double size_room;

    @NotNull(message = "Number of adults is mandatory")
    @Min(value =0, message = "Number of adults cannot be negative")
    private Integer num_adults;

    @NotNull(message = "Number of children is mandatory")
    @Min(value = 0, message = "Number of children cannot be negative")
    private Integer num_children;
}
