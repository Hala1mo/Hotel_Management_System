package com.example.hotel_management_system.models;


import com.example.hotel_management_system.models.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //automatically generates a constructor with a parameter for each field in the class
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="firstName",nullable = false)
    private String firstName;
    @Column(name="lastName",nullable = false)
    private String lastName;
    @Column(name="email",nullable = false)
    private String email;
    @Column(name="phoneNumber",nullable = false)
    private String phoneNumber;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="role",nullable = false)
    private Role role;


}
