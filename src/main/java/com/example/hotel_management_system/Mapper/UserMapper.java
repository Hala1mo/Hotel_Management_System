package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.User.RegisterDTO;
import com.example.hotel_management_system.DTO.User.UserDTO;
import com.example.hotel_management_system.DTO.User.UserResponseDTO;
import com.example.hotel_management_system.Models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserMapper {

    public static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public static UserResponseDTO mapToDTO(User user){
        return mapToDTO(user, null);
    }
    public static UserResponseDTO mapToDTO(User user,String token) {
        UserResponseDTO registerDTO = new UserResponseDTO();
        registerDTO.setFirstName(user.getFirstName());
        registerDTO.setLastName(user.getLastName());
        registerDTO.setPhoneNumber(user.getPhoneNumber());
        registerDTO.setEmail(user.getEmail());
        registerDTO.setRole(user.getRole());
        registerDTO.setToken(token);
        return registerDTO;
    }


    public static UserDTO mapToDTO2(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    // convert DTO to entity
    public static User mapToEntity(RegisterDTO registerDTO) {
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setEmail(registerDTO.getEmail());
        user.setRole(registerDTO.getRole());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return user;
    }
}
