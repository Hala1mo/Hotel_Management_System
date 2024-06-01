package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.Models.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setId(employee.getId());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDepartment(employee.getDepartment());
        return employeeDTO;
    }

    // convert DTO to entity
    public static Employee mapToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setSalary(employeeDTO.getSalary());
        return employee;
    }
}
