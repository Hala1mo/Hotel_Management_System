package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.Reserve_Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Reservation_RoomRepository extends JpaRepository<Reserve_Room, Long> {

List<Reserve_Room> findAllByBooking(Reservation Booking);
}