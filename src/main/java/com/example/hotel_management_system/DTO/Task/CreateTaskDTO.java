package com.example.hotel_management_system.DTO.Task;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;


@Data
public class CreateTaskDTO {

    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private LocalDate scheduledDate;
    @NotNull
    @NotBlank
    private Long  employee_id;

    @NotNull
    @NotBlank
    private Long  room_id;
}
