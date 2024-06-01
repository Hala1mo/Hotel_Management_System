package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.InvoiceDTO;
import com.example.hotel_management_system.DTO.InvoiceDetailsDTO;
import com.example.hotel_management_system.Mapper.InvoiceMapper;
import com.example.hotel_management_system.Models.Invoice;
import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Repository.BedTypeRepository;
import com.example.hotel_management_system.Repository.InvoiceRepository;
import com.example.hotel_management_system.Repository.ReservationRepository;
import com.example.hotel_management_system.Repository.RoomTypeRepository;
import com.example.hotel_management_system.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    InvoiceRepository invoiceRepository;
    ReservationRepository reservationRepository;


    @Autowired
    public InvoiceServiceImpl( InvoiceRepository invoiceRepository,ReservationRepository reservationRepository) {
        this.invoiceRepository=invoiceRepository;
        this.reservationRepository=reservationRepository;
    }

    InvoiceDetailsDTO retrieveInvoiceForSpecificBooking(long id){
        Reservation booking=reservationRepository.findAllById(id);
        Invoice invoice=invoiceRepository.findAllByBooking(booking);
        return InvoiceMapper.mapToInvoiceDetailsDTO(invoice);
    }

}
