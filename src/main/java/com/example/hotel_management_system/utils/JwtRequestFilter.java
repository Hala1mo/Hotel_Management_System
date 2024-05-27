package com.example.hotel_management_system.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.util.StringUtils;
import java.io.IOException;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
            final String requestTokenHeader = request.getHeader("Authorization");

            System.out.println(requestTokenHeader);
              String username = null;
              String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtils.extractUsername(jwtToken);
                System.out.println("Username extracted: " + username);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            System.out.println("JWT Token does not begin with Bearer String");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtils.validateJwtToken(jwtToken)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                System.out.println("Authentication successful for user: " + username);
            } else {
                System.out.println("JWT Token is invalid");
            }
        }
        chain.doFilter(request, response);
    }

//@Override
//protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//    try {
//        String jwt = parseJwt(request);
//        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//            String username = jwtUtils.extractUsername(jwt);
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//    } catch (Exception e) {
//        logger.error("Cannot set owner authentication: {e}", e);
//    }
//
//    filterChain.doFilter(request, response);
//}

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String jwt = parseJwt(request);
//            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//                String username = jwtUtils.extractUsername(jwt);
//
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(
//                                userDetails,
//                                null,
//                                userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception e) {
//            logger.error("Cannot set owner authentication: {e}", e);
//        }
//            filterChain.doFilter(request, response);
//    }


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null; // or throw an exception, depending on your requirements
    }
}
//}



//    @Override
//    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//        try {
//            final String requestTokenHeader = request.getHeader("Authorization");
//
//            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
//                String jwt = parseJwt(request);
//                String username = jwtUtils.extractUsername(jwt);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                if (jwtUtils.validateToken(jwt, userDetails)) {
//                    UsernamePasswordAuthenticationToken authentication =
//                            new UsernamePasswordAuthenticationToken(
//                                    userDetails,
//                                    null,
//                                    userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Cannot set owner authentication: {e}", e);
//        }
//
//        filterChain.doFilter(request, response);
//    }