package com.example.hotel_management_system.Mapper;

import com.example.hotel_management_system.DTO.Reservation.InvoiceDetailsDTO;
import com.example.hotel_management_system.Models.Invoice;
import com.example.hotel_management_system.Models.Reservation;

public class InvoiceMapper {
    public static InvoiceDetailsDTO mapToInvoiceDetailsDTO(Invoice request){
        InvoiceDetailsDTO invoiceDetails=new InvoiceDetailsDTO();
        invoiceDetails.setId(request.getBooking().getId());
        invoiceDetails.setCheckOutDate(request.getBooking().getCheckOutDate());
        invoiceDetails.setCheckInDate(request.getBooking().getCheckInDate());
        invoiceDetails.setNum_children(request.getBooking().getNum_children());
        invoiceDetails.setNum_adults(request.getBooking().getNum_adults());
        invoiceDetails.setTotalPrice(request.getPrice());
        invoiceDetails.setStatusOfReservation(request.getBooking().getStatus());
        return invoiceDetails;
    }
    public static Invoice toEntity(Reservation booking,Double price){
     return  Invoice.builder()
             .booking(booking)
             .price(price)
             .build();
    }
    public static Invoice update (Reservation booking,Invoice invoice){
        invoice.setBooking(booking);
        invoice.setPrice(invoice.getPrice());
        return invoice;
    }

}
