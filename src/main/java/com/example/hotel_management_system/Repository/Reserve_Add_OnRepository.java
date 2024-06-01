package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.Reserve_Add_On;
import com.example.hotel_management_system.Models.Room_Type_Bed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Reserve_Add_OnRepository extends JpaRepository<Reserve_Add_On, Long> {
    List<Reserve_Add_On> findAllByBooking(Reservation booking);
}