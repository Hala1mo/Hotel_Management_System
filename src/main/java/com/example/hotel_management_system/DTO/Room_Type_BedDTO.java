package com.example.hotel_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room_Type_BedDTO {
    private long id;


    @NotNull
    private long type_id;


    @NotNull
    private long bed_id;

    @NotNull
    private int num_beds;
}
