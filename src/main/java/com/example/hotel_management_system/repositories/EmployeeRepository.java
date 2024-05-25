package com.example.hotel_management_system.repositories;

import com.example.hotel_management_system.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
