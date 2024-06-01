package com.example.hotel_management_system.controllers;

import com.example.hotel_management_system.DTO.User.*;
import com.example.hotel_management_system.Security.auth.AuthenticationFacade;
import com.example.hotel_management_system.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private final AuthenticationFacade authenticationFacade;

    public UserController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping("/auth/signup")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO signup(@RequestBody RegisterDTO registerDTO) {
        return userService.signup(registerDTO);
    }

    @PostMapping("auth/login")
    public UserResponseDTO authenticate(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getCustomerById(@PathVariable long id) {
        UserDTO customer = userService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    //View Profile
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        if (authentication.getCredentials() != null) {
            String token = authentication.getCredentials().toString();
            System.out.println("Token: " + token);
        } else {
            System.out.println("Token is null");
        }
        return userService.findByEmail(email);
    }

    //updateProfile
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile/updateProfile")
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UpdateUserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getCredentials() != null) {
            String email = authentication.getName();
            UserDTO user = userService.findByEmail(email);
            UserDTO updatedCustomer = userService.updateProfile(user.getId(), userDTO);
            return ResponseEntity.ok(updatedCustomer);
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
        userService.deleteCustomerById(id);
        return ResponseEntity.ok("Deleted successfully");
    }


    //Change password
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile/changePassword")
    public ResponseEntity<String> changePassword( @RequestBody PasswordChangeDTO passwordChangeDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getCredentials() != null) {
            String email = authentication.getName();
            UserDTO user=userService.findByEmail(email);
            userService.changePassword(user.getId(), passwordChangeDTO);
            return ResponseEntity.ok("Password changed successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to change the password for this user");
        }
    }


    //Get all customers
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers")
    public List<UserDTO> getAllCustomers() {

        return userService.getAllCustomers();
    }

    //Get all admins
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admins")
    public List<UserDTO> getAllAdmins() {
        return userService.getAllAdmins();
    }

}
