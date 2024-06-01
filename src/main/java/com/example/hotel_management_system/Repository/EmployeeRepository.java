package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();
    Employee findById(long id);
    Employee findByEmail(String email);
}
