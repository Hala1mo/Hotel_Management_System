package com.example.hotel_management_system.DTO.Task;

import com.example.hotel_management_system.Models.Enum.TaskStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;


@Data
public class TaskDTO {

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
    @NotNull
    @Enumerated
    private TaskStatus status;
}
