package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Employee.EmployeeDTO;
import com.example.hotel_management_system.DTO.Employee.EmployeeDTO2;
import com.example.hotel_management_system.DTO.Employee.InsertEmployeeDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Get all employees")
    @GetMapping("")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted an employee",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No employees", content = @Content),
    })
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get an employee by ID (version 1)")
    @GetMapping("/v1/id/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a employee by ID",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid employee ID", content = @Content)
    })
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Get an employee by ID (version 2)")
    @GetMapping("/v2/id/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a employee by ID",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid employee ID", content = @Content)
    })
    public EmployeeDTO2 getEmployeeByIdV2(@PathVariable Long id) {
        return employeeService.getEmployeeByIdV2(id);
    }

    @Operation(summary = "Get an employee by email")
    @GetMapping("/email/{email}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a employee by email",
                    content = {@Content(mediaType = "application/json",schema= @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid employee email", content = @Content)
    })
    public EmployeeDTO getEmployeeByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    @Operation(summary = "Create a new employee")
    @PostMapping("/create")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Successfully created a new employee",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    })
    public EmployeeDTO addEmployee(@Valid @RequestBody InsertEmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @Operation(summary = "Update an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated an employee",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid employee ID", content = @Content)
    })
    @PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @Operation(summary = "Delete an employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted an employee", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid employee ID", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @Operation(summary = "Get employees for a specific department")
    @GetMapping("/department/{department}")
    @ApiResponses(value = {@ApiResponse (responseCode = "200",description = "Successfully retrieved all employees for specific department", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    @ApiResponse(responseCode = "404", description = "No Employees found in this department", content = @Content)
})
    public List<EmployeeDTO> getEmployeesForSpecificDepartment(@PathVariable String department) {
        return employeeService.getEmployeeForSpecifDepartment(department);
    }
}
