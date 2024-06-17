package com.example.hotel_management_system.DTO.Employee;

import com.example.hotel_management_system.Models.Enum.Department;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class InsertEmployeeDTO {


    @Valid

    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits")
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;
    @NotBlank(message = "Address is mandatory")
    private String address;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @jakarta.validation.constraints.NotNull(message = "Salary is mandatory")
    private long salary;

    @jakarta.validation.constraints.NotNull(message = "Hire Date is mandatory")
    private LocalDate hireDate;

    @jakarta.validation.constraints.NotNull(message = "Department is mandatory")
    @Enumerated
    private Department department;

}
