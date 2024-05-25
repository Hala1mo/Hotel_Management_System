package com.example.hotel_management_system.DTO;

import com.example.hotel_management_system.Models.Enum.Role;
import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}
