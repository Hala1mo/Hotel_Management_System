package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserve_RoomDTO {

    private long id;

    @NotNull
    private long room_id;
}
