package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Models.Room_Type;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RoomDTO{
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

}