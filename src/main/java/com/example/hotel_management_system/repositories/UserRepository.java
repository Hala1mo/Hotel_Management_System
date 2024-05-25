package com.example.hotel_management_system.repositories;

import com.example.hotel_management_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findById(long id);
    Optional<User> findByEmail(String email);


}
