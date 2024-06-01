package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    User findAllById(long id);
    @Query("SELECT c FROM User c WHERE c.firstName = :name OR c.id = :id")
    User findByNameOrId(@Param("name") String name, @Param("id") Long id);
    List<User> findAll();
    User findById(long id);
    Optional<User> findByEmail(String email);
}