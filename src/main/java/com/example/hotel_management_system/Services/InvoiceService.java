package com.example.hotel_management_system.Services;

import com.example.hotel_management_system.DTO.InvoiceDetailsDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceDetailsDTO retrieveInvoiceForSpecificBooking(long id);

}
