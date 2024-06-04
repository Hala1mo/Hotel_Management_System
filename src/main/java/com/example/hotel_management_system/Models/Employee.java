package com.example.hotel_management_system.Models;

import com.example.hotel_management_system.Models.Enum.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor //automatically generates a constructor with a parameter for each field in the class
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="firstName",nullable = false)
    private String firstName;
    @Column(name="lastName",nullable = false)
    private String lastName;
    @Column(name="phoneNumber",nullable = false)
    private String phoneNumber;
    @Column(name="address",nullable = false)
    private String address;
    @Column(name="hireDate",nullable = false)
    private LocalDate hireDate;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="salary",nullable = false)
    private long salary;
    @Column(name="department",nullable = false)
    private Department department;

}
