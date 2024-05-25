package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.LoginDTO;
import com.example.hotel_management_system.DTO.PasswordChangeDTO;
import com.example.hotel_management_system.DTO.UserDTO;
import com.example.hotel_management_system.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    UserDTO createCustomer(UserDTO userDTO);

    List<UserDTO> getAllCustomers();

    UserDTO getCustomerById(long id);

    UserDTO updateCustomer(long id, UserDTO userDTO);

    void deleteCustomerById(long id);

    void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO);


    String signup(UserDTO userDTO);

    UserDetails loadUserByUsername(String email);

    User login(LoginDTO loginDTO);

    UserDTO currentUser();
}
