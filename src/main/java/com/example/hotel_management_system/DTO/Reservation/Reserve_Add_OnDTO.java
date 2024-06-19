package com.example.hotel_management_system.DTO.Reservation;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserve_Add_OnDTO {
    private long id;

    @NotNull
    private int add_on_id;
}
