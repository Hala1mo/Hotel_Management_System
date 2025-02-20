package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Reservation.InvoiceDetailsDTO;
import com.example.hotel_management_system.Mapper.InvoiceMapper;
import com.example.hotel_management_system.Models.Invoice;
import com.example.hotel_management_system.Models.Reservation;
import com.example.hotel_management_system.Repository.InvoiceRepository;
import com.example.hotel_management_system.Repository.ReservationRepository;
import com.example.hotel_management_system.Services.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    InvoiceRepository invoiceRepository;
    ReservationRepository reservationRepository;


    @Autowired
    public InvoiceServiceImpl( InvoiceRepository invoiceRepository,ReservationRepository reservationRepository) {
        this.invoiceRepository=invoiceRepository;
        this.reservationRepository=reservationRepository;
    }

   public InvoiceDetailsDTO retrieveInvoiceForSpecificBooking(long id){
        Reservation booking=reservationRepository.findAllById(id);
        if (booking==null) {
            throw new EntityNotFoundException("No Reservation found with this id");
        }
        Invoice invoice=invoiceRepository.findAllByBooking(booking);
        if (invoice==null) {
            throw new EntityNotFoundException("No Invoice found for this booking");
        }
        return InvoiceMapper.mapToInvoiceDetailsDTO(invoice);
    }

}
