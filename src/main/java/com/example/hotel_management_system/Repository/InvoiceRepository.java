package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Invoice;
import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Models.Reserve_Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
Invoice findAllByBooking(Reservation book);
}