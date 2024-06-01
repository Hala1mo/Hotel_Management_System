package com.example.hotel_management_system.DTO.User;


import com.example.hotel_management_system.Models.Enum.Role;
import lombok.Data;

@Data
public class UserResponseDTO {

     String firstName;

     String lastName;

     String phoneNumber;
     String email;

     String password;

     Role role;

     String token;
}
