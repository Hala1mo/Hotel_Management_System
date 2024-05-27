package com.example.hotel_management_system.DTO;


import com.example.hotel_management_system.Models.Enum.Department;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class EmployeeDTO {
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private long salary;

    @NotNull
    @NotBlank
    private LocalDate hireDate;
    @NotNull
    @Enumerated
    private Department department;

}
