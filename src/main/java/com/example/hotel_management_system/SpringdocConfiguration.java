package com.example.hotel_management_system;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SpringdocConfiguration {

    @Bean
    public OpenAPI baseOpenAPI(){
        return new OpenAPI().info(new Info().
                title("Hotel Management ").
                version("1.0.0").
                description("The Hotel Management System is designed to streamline operations for both hotel administrators and guests. It offers functionalities for customers such as searching for available rooms, making reservations, customer check-ins and check-outs, and invoice generation. Administrators can manage hotel employees and staff, room availability, and housekeeping schedules. The system includes role-based access control, providing different functionalities based on user roles (admin, customer)."));

    }
}
