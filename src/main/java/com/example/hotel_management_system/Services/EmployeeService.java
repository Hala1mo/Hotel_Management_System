package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Employee.EmployeeDTO;
import com.example.hotel_management_system.DTO.Employee.EmployeeDTO2;
import com.example.hotel_management_system.DTO.Employee.InsertEmployeeDTO;

import java.util.List;

public interface EmployeeService {


    EmployeeDTO createEmployee(InsertEmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(long id);

    EmployeeDTO2 getEmployeeByIdV2(long id);

    EmployeeDTO getEmployeeByEmail(String email);

    List<EmployeeDTO> getEmployeeForSpecifDepartment(String department);

    EmployeeDTO updateEmployee(long id, InsertEmployeeDTO employeeDTO);

    void deleteEmployeeById(long id);
}
