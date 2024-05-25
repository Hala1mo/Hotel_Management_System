package com.example.hotel_management_system.Repository;


import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.Room_Type_Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
