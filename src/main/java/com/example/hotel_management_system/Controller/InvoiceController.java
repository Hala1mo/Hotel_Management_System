package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.InvoiceDetailsDTO;
import com.example.hotel_management_system.DTO.Room.FeatureDTO;
import com.example.hotel_management_system.Services.InvoiceService;
import com.example.hotel_management_system.Services.RoomFeaturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/reservations/Invoice")
public class InvoiceController {
   InvoiceService invoiceService;

    @Autowired
    public InvoiceController( InvoiceService invoiceService){

        this.invoiceService=invoiceService;
    }
    @GetMapping("Booking/{id}")
    public  InvoiceDetailsDTO retrieveInvoiceForSpecificBooking(@PathVariable long id){
        return invoiceService.retrieveInvoiceForSpecificBooking(id);
    }


}
