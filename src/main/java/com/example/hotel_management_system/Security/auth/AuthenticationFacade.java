package com.example.hotel_management_system.Security.auth;
import com.example.hotel_management_system.Models.User;
import org.springframework.security.core.Authentication;
public interface AuthenticationFacade {
    Authentication getAuthentication();
    User getAuthenticatedUser();
    User getAuthenticatedUserOrNull();
}