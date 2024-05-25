package com.example.hotel_management_system.Models;

import com.example.hotel_management_system.Models.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

