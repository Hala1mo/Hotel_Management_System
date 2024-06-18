package com.example.hotel_management_system.Repository;


import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
Reservation findAllById(long id);
List<Reservation> findAllByUser(User user);
    @Query("SELECT r FROM Reservation r " +
            "JOIN r.booking_room rr " +
            "WHERE rr.room_id.id = :roomId " +
            "AND r.checkOutDate > :checkInDate " +
            "AND r.checkInDate < :checkOutDate")
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                  @Param("checkInDate") Date checkInDate,
                                                  @Param("checkOutDate") Date checkOutDate);


}
