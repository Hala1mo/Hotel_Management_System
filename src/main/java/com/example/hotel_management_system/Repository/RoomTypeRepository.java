package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Models.Room_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoomTypeRepository extends JpaRepository<Room_Type, Long> {
    Room_Type findAllById(long id);
}
