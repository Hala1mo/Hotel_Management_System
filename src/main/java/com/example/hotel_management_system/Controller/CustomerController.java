package com.example.hotel_management_system.Controller;

import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping("")
    public String retrieveCustomers(){
        return "hala";
    }



}

