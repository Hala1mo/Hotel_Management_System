package com.example.hotel_management_system.DTO.Task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTaskDTO {

    @Valid
    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Scheduled Date is mandatory")
    private LocalDate scheduledDate;

    @NotNull(message = "Employee Id is mandatory")
    private Long employee_id;

    @NotNull(message = "Room Id is mandatory")
    private Long room_id;
}
