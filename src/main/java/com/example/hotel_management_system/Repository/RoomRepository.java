package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findAllById(long id);
    List<Room> findAllByStatus(roomStatus status);
    List<Room> findAllByView(roomView view);
}
