package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Models.Enum.roomView;
import com.example.hotel_management_system.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findAllById(long id);
    List<Room> findAllByStatus(roomStatus status);
    List<Room> findAllByView(roomView view);
    @Query("SELECT DISTINCT r FROM Room r " +
            "WHERE r.status = 0 or r.status = 1  AND NOT EXISTS (" +
            "   SELECT 1 FROM Reserve_Room rr " +
            "   JOIN rr.booking res " +
            "   WHERE rr.room_id = r AND res.status != 3 AND (" +
            "       (res.checkInDate BETWEEN :checkInDate AND :checkOutDate) OR " +
            "       (res.checkOutDate BETWEEN :checkInDate AND :checkOutDate) OR " +
            "       (res.checkInDate <= :checkInDate AND res.checkOutDate >= :checkOutDate)" +
            "   )" +
            ")")


    List<Room> findAvailableRooms(@Param("checkInDate") Date checkInDate,
                                  @Param("checkOutDate") Date checkOutDate);}

