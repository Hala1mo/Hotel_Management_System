package com.example.hotel_management_system.Repository;


import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
Reservation findAllById(long id);
List<Reservation> findAllByUser(User user);

}
