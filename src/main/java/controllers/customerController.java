package controllers;


import Services.customerService;
import dto.customerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class customerController {

    private customerService customerService;

    @Autowired
    public customerController(customerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<customerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @PostMapping("")
    public customerDTO saveNewCustomer( @RequestBody customerDTO customerDTO) {

        return customerService.createCustomer(customerDTO);
    }


}


