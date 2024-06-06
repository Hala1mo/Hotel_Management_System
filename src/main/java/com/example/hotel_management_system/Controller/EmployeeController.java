package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Employee.EmployeeDTO;
import com.example.hotel_management_system.DTO.Employee.InsertEmployeeDTO;
import com.example.hotel_management_system.Services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public EmployeeDTO addEmployee(@Valid  @RequestBody InsertEmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO updatedEmployee) {
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
