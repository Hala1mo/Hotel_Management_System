package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.User.*;
import com.example.hotel_management_system.Security.auth.AuthenticationFacade;
import com.example.hotel_management_system.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @Operation(summary = "Create a new user account")
    @ApiResponse(responseCode = "201", description = "User account created",
            content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    @PostMapping("/auth/signup")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO signup(@Valid @RequestBody RegisterDTO registerDTO) {
        return userService.signup(registerDTO);
    }

    @Operation(summary = "Authenticate and login")
    @ApiResponse(responseCode = "200", description = "User authenticated",
            content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    @PostMapping("auth/login")
    public UserResponseDTO authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @Operation(summary = "Get a customer by ID")
    @ApiResponse(responseCode = "200", description = "Found the customer",
            content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDTO> getCustomerById(@PathVariable long id) {
        UserDTO customer = userService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(summary = "View user profile")
    @ApiResponse(responseCode = "200", description = "User profile retrieved",
            content = @Content(schema = @Schema(implementation = UserDTO.class)))
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

    @Operation(summary = "Update user profile")
    @ApiResponse(responseCode = "200", description = "User profile updated",
            content = @Content(schema = @Schema(implementation = UserDTO.class)))
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile/updateProfile")
    public ResponseEntity<UserDTO> updateProfile(@Valid @RequestBody UpdateUserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getCredentials() != null) {
            String email = authentication.getName();
            UserDTO user = userService.findByEmail(email);
            UserDTO updatedCustomer = userService.updateProfile(user.getId(), userDTO);
            return ResponseEntity.ok(updatedCustomer);
        }
        return null;
    }

    @Operation(summary = "Delete a user")
    @ApiResponse(responseCode = "200", description = "User deleted")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userService.deleteCustomerById(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @Operation(summary = "Change user password")
    @ApiResponse(responseCode = "200", description = "Password changed")
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile/changePassword")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PasswordChangeDTO passwordChangeDTO) {
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

    @Operation(summary = "Get all customers")
    @ApiResponse(responseCode = "200", description = "List of customers",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers")
    public List<UserDTO> getAllCustomers() {
        return userService.getAllCustomers();
    }

    @Operation(summary = "Get all admins")
    @ApiResponse(responseCode = "200", description = "List of admins",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))))
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admins")
    public List<UserDTO> getAllAdmins() {
        return userService.getAllAdmins();
    }

}
