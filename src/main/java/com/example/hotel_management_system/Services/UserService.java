package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.User.*;

import java.util.List;


public interface UserService {


    UserDTO getCustomerById(long id);

   UserDTO updateProfile(long id, UpdateUserDTO updateUserDTO);

   void deleteCustomerById(long id);

   void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO);


    UserResponseDTO signup(RegisterDTO registerDTO);

    UserResponseDTO login(LoginDTO loginDTO);

    List<UserDTO> getAllCustomers();

    List<UserDTO> getAllAdmins();
    UserDTO findByEmail(String email);


}
