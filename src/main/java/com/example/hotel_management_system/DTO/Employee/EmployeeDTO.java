package com.example.hotel_management_system.DTO.Employee;


import com.example.hotel_management_system.Models.Enum.Department;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class EmployeeDTO {


    @NotNull
    @NotBlank
    private long id;

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
