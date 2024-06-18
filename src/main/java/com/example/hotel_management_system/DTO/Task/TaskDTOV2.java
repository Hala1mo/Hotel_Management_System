package com.example.hotel_management_system.DTO.Task;

import com.example.hotel_management_system.Models.Enum.TaskStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
@Data
public class TaskDTOV2 {
        private Long id;
        @Valid
        @NotBlank(message = "Description is mandatory")
        private String description;
        @NotBlank(message = "Scheduled date is mandatory")
        private LocalDate scheduledDate;
        @NotBlank(message = "Employee id is mandatory")
        private Long  employee_id;

        @NotBlank(message = "Room id is mandatory")
        private Long  room_id;
        @NotNull(message = "Task status is mandatory")
        @Enumerated
        private TaskStatus status;


        private int floor_number;
    }
