package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.LoginDTO;
import com.example.hotel_management_system.DTO.PasswordChangeDTO;
import com.example.hotel_management_system.DTO.UserDTO;
import com.example.hotel_management_system.Models.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllCustomers();

    UserDTO getCustomerById(long id);

    UserDTO updateCustomer(long id, UserDTO userDTO);

    void deleteCustomerById(long id);

    void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO);


    UserDTO signup(UserDTO userDTO );

    UserDTO login(LoginDTO loginDTO);


}
