package com.example.hotel_management_system.Security.auth;

import com.example.hotel_management_system.Exceptions.HttpNotFoundException;
import com.example.hotel_management_system.models.User;
import com.example.hotel_management_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {

        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User getAuthenticatedUser() {

            String email = getAuthentication().getName();


        return userRepository.findByEmail(email)
                .orElseThrow(() -> new HttpNotFoundException("User not found with email " + email));
    }

    @Override
    public User getAuthenticatedUserOrNull() {
        String email = getAuthentication().getName();
        if ("anonymousUser".equals(email)) {
            return null;
        }
        return userRepository.findByEmail(email).orElse(null);
    }
}
