package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.Reservation.InvoiceDetailsDTO;

public interface InvoiceService {
    InvoiceDetailsDTO retrieveInvoiceForSpecificBooking(long id);

}
