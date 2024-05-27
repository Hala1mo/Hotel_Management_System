package com.example.hotel_management_system.Services.impl;

import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.DTO.UserDTO;
import com.example.hotel_management_system.Mapper.EmployeeMapper;
import com.example.hotel_management_system.Mapper.UserMapper;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.User;
import com.example.hotel_management_system.Services.EmployeeService;
import com.example.hotel_management_system.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

     @Autowired
      EmployeeRepository employeeRepository;
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEntity(employeeDTO);
        Employee newUser = employeeRepository.save(employee);
        return EmployeeMapper.mapToDTO(newUser);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id);
        return EmployeeMapper.mapToDTO(employee);
    }


    @Override
    public void deleteEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id);
        employeeRepository.delete(employee);

    }
    @Override
    public EmployeeDTO updateEmployee(long id, EmployeeDTO employeeDTO) {
        Employee employee= employeeRepository.findById(id);
              if(employee!=null){
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setEmail(employeeDTO.getEmail());
                    employee.setPhoneNumber(employeeDTO.getPhoneNumber());
                    employee.setAddress(employeeDTO.getAddress());
                    employee.setDepartment(employeeDTO.getDepartment());
                    Employee updatedEmployee =employeeRepository.save(employee);
                    return EmployeeMapper.mapToDTO(updatedEmployee);
                }
               return null;
    }

}
