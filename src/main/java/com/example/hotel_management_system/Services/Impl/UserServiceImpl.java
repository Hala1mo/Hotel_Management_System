package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.User.*;
import com.example.hotel_management_system.Error.UserNotFoundException;
import com.example.hotel_management_system.Models.User;
import com.example.hotel_management_system.Repository.UserRepository;
import com.example.hotel_management_system.Security.auth.AuthenticationFacade;
import com.example.hotel_management_system.utils.JwtUtil;
import jakarta.validation.constraints.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hotel_management_system.Services.UserService;
import com.example.hotel_management_system.Mapper.UserMapper;
import org.springframework.security.core.Authentication;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;


@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    public UserServiceImpl(AuthenticationManager authenticationManager,
                           AuthenticationFacade authenticationFacade,
                           UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authenticationFacade=authenticationFacade;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public UserResponseDTO signup(RegisterDTO registerDTO) {
        userRepository.save(UserMapper.mapToEntity(registerDTO));
        return login(new LoginDTO(registerDTO.getEmail(), registerDTO.getPassword()));

    }

    public UserResponseDTO login(@NotNull LoginDTO loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            logger.info("User authenticated: {}", userPrincipal.getUsername());
            String jwt = jwtUtil.generateToken(authentication);
            System.out.println(jwt);
            return UserMapper.mapToDTO(user, jwt);
        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", e.getMessage());
            throw new BadCredentialsException("Invalid password");
        } catch (Exception e) {
            logger.error("Exception during login: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public UserDTO getCustomerById(long id) {

        User user = userRepository.findById(id);
        return UserMapper.mapToDTO2(user);
    }

    @Override
    public UserDTO updateProfile(long id, UpdateUserDTO userDTO) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setEmail(userDTO.getEmail());
            User updatedUser =userRepository.save(user);
            return UserMapper.mapToDTO2(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteCustomerById(long id) {
        User user = userRepository.findById(id);
        userRepository.delete(user);

    }


    public void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public List<UserDTO> getAllCustomers() {
        List<User> queryResult = userRepository.findAll();
        List<UserDTO> customers = new ArrayList<>();
        for (User user : queryResult) {
            if(user.getRole().toString().equals("ROLE_USER")) {
                UserDTO userDTO = UserMapper.mapToDTO2(user);
                customers.add(userDTO);
            }
        }
        return customers;
    }

    @Override
    public List<UserDTO> getAllAdmins() {
        List<User> queryResult = userRepository.findAll();
        List<UserDTO> admins = new ArrayList<>();
        for (User user : queryResult) {
            if(user.getRole().toString().equals("ROLE_ADMIN")) {
                UserDTO userDTO = UserMapper.mapToDTO2(user);
                admins.add(userDTO);
            }
        }
        return admins;
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserMapper.mapToDTO2(user);

    }





}