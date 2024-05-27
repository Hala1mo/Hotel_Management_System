package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.Models.Employee;

import java.util.List;

public interface EmployeeService {


    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<Employee> getAllEmployees();

    EmployeeDTO getEmployeeById(long id);

    EmployeeDTO updateEmployee(long id, EmployeeDTO employeeDTO);

    void deleteEmployeeById(long id);
}
