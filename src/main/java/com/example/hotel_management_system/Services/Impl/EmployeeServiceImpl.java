package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Employee.EmployeeDTO;
import com.example.hotel_management_system.DTO.Employee.InsertEmployeeDTO;
import com.example.hotel_management_system.Mapper.EmployeeMapper;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Services.EmployeeService;
import com.example.hotel_management_system.Repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

     @Autowired
      EmployeeRepository employeeRepository;
    @Override
    public EmployeeDTO createEmployee(InsertEmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.mapToEntity(employeeDTO);
        Employee newUser = employeeRepository.save(employee);
        return EmployeeMapper.mapToDTO(newUser);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> queryResult = employeeRepository.findAll();
        List<EmployeeDTO> employees = new ArrayList<>();
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("No Employees found in the database.");
        }

        for (Employee employee : queryResult) {
            EmployeeDTO employeeDTO = EmployeeMapper.mapToDTO(employee);
            employees.add(employeeDTO);
        }
        return employees;
    }
    @Override
    public EmployeeDTO getEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee==null) {
            throw new EntityNotFoundException("No Employee found with this id");
        }
        return EmployeeMapper.mapToDTO(employee);
    }

    @Override
    public EmployeeDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee==null) {
            throw new EntityNotFoundException("No Employee found with this email");
        }
        return EmployeeMapper.mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeForSpecifDepartment(String department) {
        List<Employee> queryResult = employeeRepository.findAll();
        if (queryResult.isEmpty()) {
            throw new EntityNotFoundException("No Employees found in this department.");
        }
        List<EmployeeDTO> employees = new ArrayList<>();
        for (Employee employee : queryResult) {
            if(employee.getDepartment().toString().equals(department)) {
                EmployeeDTO employeeDTO = EmployeeMapper.mapToDTO(employee);
                employees.add(employeeDTO);
            }
        }
        return employees;
    }


    @Override
    public void deleteEmployeeById(long id) {
        Employee employee = employeeRepository.findById(id);
        if(employee!=null) {
            employeeRepository.delete(employee);
        }
          else{
            throw new EntityNotFoundException("Employee not found with id: " + id);
        }
    }
    @Override
    public EmployeeDTO updateEmployee(long id, EmployeeDTO employeeDTO) {
        Employee employee= employeeRepository.findById(id);
              if(employee!=null){
                    employee.setFirstName(employeeDTO.getFirstName());
                    employee.setLastName(employeeDTO.getLastName());
                    employee.setEmail(employeeDTO.getEmail());
                    employee.setPhoneNumber(employeeDTO.getPhoneNumber());
                    employee.setSalary(employeeDTO.getSalary());
                    employee.setHireDate(employeeDTO.getHireDate());
                    employee.setAddress(employeeDTO.getAddress());
                    employee.setDepartment(employeeDTO.getDepartment());
                    Employee updatedEmployee =employeeRepository.save(employee);
                    return EmployeeMapper.mapToDTO(updatedEmployee);
                }
              else {
                  throw new EntityNotFoundException("Employee not found with id: " + id);
              }
    }

}
