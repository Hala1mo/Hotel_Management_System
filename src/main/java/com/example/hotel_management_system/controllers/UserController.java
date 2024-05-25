package com.example.hotel_management_system.controllers;

import com.example.hotel_management_system.Mapper.UserMapper;
import com.example.hotel_management_system.Security.auth.AuthenticationFacade;
import com.example.hotel_management_system.Services.UserService;
import com.example.hotel_management_system.dto.LoginDTO;
import com.example.hotel_management_system.dto.PasswordChangeDTO;
import com.example.hotel_management_system.dto.UserDTO;
import com.example.hotel_management_system.models.User;
import com.example.hotel_management_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;
    private final AuthenticationFacade authenticationFacade;

    public UserController(AuthenticationFacade authenticationFacade) {

        this.authenticationFacade = authenticationFacade;
    }


    @PostMapping("/auth/signup")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) {
        String token = userService.signup(userDTO);
        return ResponseEntity.ok(token);
    }

    @PostMapping("auth/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginDTO) {
        // Authenticate user
        User authenticatedUser = userService.login(loginDTO);
        String token = jwtUtil.generateToken(authenticatedUser);
        return ResponseEntity.ok(token);
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
