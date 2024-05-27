package com.example.hotel_management_system.repositories;

import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findById(long id);
}
