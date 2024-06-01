package com.example.hotel_management_system.Models;

import com.example.hotel_management_system.Models.Enum.CleanlinessStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor //automatically generates a constructor with a parameter for each field in the class
@Entity
@Table(name="Rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name="cleanliness_status",nullable = false)
    private CleanlinessStatus cleanlinessStatus;

}
