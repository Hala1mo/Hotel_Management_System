package com.example.hotel_management_system.controllers;

import com.example.hotel_management_system.Mapper.UserMapper;
import com.example.hotel_management_system.Security.auth.AuthenticationFacade;
import com.example.hotel_management_system.Services.UserService;
import com.example.hotel_management_system.DTO.LoginDTO;
import com.example.hotel_management_system.DTO.PasswordChangeDTO;
import com.example.hotel_management_system.DTO.UserDTO;
import com.example.hotel_management_system.Models.User;
import com.example.hotel_management_system.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
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
    public UserDTO signup(@RequestBody UserDTO userDTO) {
      //  String token = userService.signup(userDTO);
      //  return ResponseEntity.ok(token);
        return userService.signup(userDTO);
    }

    @PostMapping("auth/login")
    public UserDTO authenticate(@RequestBody LoginDTO loginDTO) {
        // Authenticate user
        UserDTO authenticatedUser = userService.login(loginDTO);
        return authenticatedUser;
    }

    @GetMapping("")
    public List<UserDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getCustomerById(@PathVariable long id) {
        System.out.println("ffjjf");
        UserDTO customer = userService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }


//        @GetMapping("/profile/{id}")
//    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Customer currentUser = (Customer) authentication.getPrincipal();
//        return ResponseEntity.ok(currentUser);
//        //return ResponseEntity.ok(customer);
//    }

//    @GetMapping("/me")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public UserDTO currentUser(){
//        return userService.currentUser();
//    }

    @GetMapping("/me")
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO currentUser() {
        System.out.println("halaaaa");
        User user = authenticationFacade.getAuthenticatedUser();
        return UserMapper.mapToDTO(user);
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<UserDTO> updateCustomer(@PathVariable long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedCustomer = userService.updateCustomer(id, userDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
        userService.deleteCustomerById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PutMapping("/profile/{userId}/password")
    public ResponseEntity<String> changePassword(@PathVariable Long userId, @RequestBody PasswordChangeDTO passwordChangeDTO) {
        userService.changePassword(userId, passwordChangeDTO);
        return ResponseEntity.ok("Password changed successfully");
    }
}
