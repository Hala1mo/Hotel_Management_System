package Services.impl;

import dto.customerDTO;
import jakarta.transaction.Transactional;
import models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.customerRepository;
import Services.customerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class customerServiceImpl  implements customerService {

    private final customerRepository customerRepository;

    public customerServiceImpl(customerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public customerDTO createCustomer(customerDTO customerDTO) {
        try {
            System.out.println("Creating customer...");
            Customer customer = mapToEntity(customerDTO);
            Customer newCustomer = customerRepository.save(customer);
            return mapToDTO(newCustomer);
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error creating customer: " + e.getMessage());
            e.printStackTrace();
            throw e; // Rethrow the exception if necessary
        }
    }

        @Override
    public List<customerDTO> getAllCustomers() {
        List<Customer> queryResult = customerRepository.findAll();
        List<customerDTO> customers = new ArrayList<>();
        for (Customer customer : queryResult) {
            customerDTO customerDTO = mapToDTO(customer);
            customers.add(customerDTO);
        }
        return customers;
    }


    @Override
    public customerDTO getCustomerById(long id) {

        Customer supplier = customerRepository.findById(id);
        return mapToDTO(supplier);
    }

    @Override
    public customerDTO updateCustomer(long id, customerDTO customerDTO) {

        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setPhoneNumber(customerDTO.getPhone_number());
            customer.setEmail(customerDTO.getEmail());
        }
        return null;
    }

    @Override
    public void deleteCustomerById(long id) {
        Customer customer = customerRepository.findById(id);
        customerRepository.delete(customer);

    }
    private customerDTO mapToDTO(Customer customer) {
        customerDTO customerDTO = new customerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setPhone_number(customer.getPhoneNumber());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    // convert DTO to entity
    private Customer mapToEntity(customerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhoneNumber(customerDTO.getPhone_number());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }
}
