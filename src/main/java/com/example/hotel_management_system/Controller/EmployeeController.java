package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.EmployeeDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/id/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/email/{email}")
    public EmployeeDTO getEmployeeByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @PostMapping("/create")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/department/{department}")
    public List<EmployeeDTO>  getEmployeesForSpecificDepartment(@PathVariable String department) {
        return employeeService.getEmployeeForSpecifDepartment(department);
    }

}
