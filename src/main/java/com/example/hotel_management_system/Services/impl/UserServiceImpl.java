package com.example.hotel_management_system.Services.impl;

import com.example.hotel_management_system.Exceptions.UserNotFoundException;
import com.example.hotel_management_system.Mapper.EmployeeMapper;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.Enum.Role;
import com.example.hotel_management_system.Security.Services.UserDetailsImpl;
import com.example.hotel_management_system.Security.auth.AuthenticationFacade;
import com.example.hotel_management_system.DTO.LoginDTO;
import com.example.hotel_management_system.DTO.PasswordChangeDTO;
import com.example.hotel_management_system.DTO.UserDTO;
import com.example.hotel_management_system.Models.User;
import com.example.hotel_management_system.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
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
import com.example.hotel_management_system.repositories.UserRepository;
import com.example.hotel_management_system.Services.UserService;
import com.example.hotel_management_system.Mapper.UserMapper;
import org.springframework.security.core.Authentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.hotel_management_system.Models.Enum.Role;

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
    public UserDTO signup(UserDTO userDTO) {
        logger.info("signing customer: {}", userDTO.getEmail());
        UserDTO createdCustomer = createUser(userDTO);
        User user=UserMapper.mapToEntity(createdCustomer);
        //return jwtUtil.generateToken(user);
        return login(new LoginDTO(userDTO.getEmail(), userDTO.getPassword()));

    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        logger.info("Creating customer: {}", userDTO.getEmail());
        User user = UserMapper.mapToEntity(userDTO);
        User newUser = userRepository.save(user);
        return UserMapper.mapToDTO(newUser);
    }

    public UserDTO login(@NotNull LoginDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
            logger.info("User authenticated: {}", userPrincipal.getUsername());
            String jwt = jwtUtil.generateToken(authentication);

            System.out.println(jwt);

            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return UserMapper.mapToDTO(user);
        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", e.getMessage());
            throw new BadCredentialsException("Invalid email or password");
        } catch (Exception e) {
            logger.error("Exception during login: {}", e.getMessage());
            throw e;
        }
    }





//    public User login(LoginDTO loginDTO) {
//        logger.info("Creating customer: {}", loginDTO.getEmail());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
////        if (!user.isPresent()) {
////            throw new UsernameNotFoundException("User not found with email: " + loginDTO.getEmail());
////        }
////        User userr = user.get();
////        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), userr.getPassword())) {
////            System.out.println("Received auth2 request");
////            return "Login Successfully";
////        }
//        return userRepository.findByEmail(loginDTO.getEmail())
//                .orElseThrow();
//    }

//    public User login(LoginDTO loginDTO) {
//        try {
//            logger.info("Authenticating user: {}", loginDTO.getEmail());
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            User user = authenticationFacade.getAuthenticatedUser();
//
//            return userRepository.findByEmail(loginDTO.getEmail())
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid email or password");
//        }
//    }



//    @Override
//    public User login(LoginDTO loginDTO) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            return userRepository.findByEmail(userDetails.getUsername())
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid email or password");
//        }
//    }


    @Override
    public List<UserDTO> getAllCustomers() {
        List<User> queryResult = userRepository.findAll();
        List<UserDTO> customers = new ArrayList<>();
        for (User user : queryResult) {
            UserDTO userDTO = UserMapper.mapToDTO(user);
            customers.add(userDTO);
        }
        return customers;
    }


    @Override
    public UserDTO getCustomerById(long id) {

        User user = userRepository.findById(id);
        return UserMapper.mapToDTO(user);
    }

    @Override
    public UserDTO updateCustomer(long id, UserDTO userDTO) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setEmail(userDTO.getEmail());
            user.setRole(userDTO.getRole());

            User updatedUser =userRepository.save(user);
            return UserMapper.mapToDTO(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteCustomerById(long id) {
        User user = userRepository.findById(id);
        userRepository.delete(user);

    }
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        User user = userOptional.get();

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("CUSTOMER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }


    public void changePassword(Long userId, PasswordChangeDTO passwordChangeDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        userRepository.save(user);
    }


    public UserDTO currentUser() {
        System.out.println("jhfjfjhfjfjfj");
        User user = authenticationFacade.getAuthenticatedUser();
        return UserMapper.mapToDTO(user);
    }


}