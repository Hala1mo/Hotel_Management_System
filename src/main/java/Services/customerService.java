package Services;

import dto.customerDTO;

import java.util.List;


public interface customerService {

    customerDTO createCustomer(customerDTO customerDTO);

    List<customerDTO> getAllCustomers();

    customerDTO getCustomerById(long id);

    customerDTO updateCustomer(long id,customerDTO customerDTO);

    void deleteCustomerById(long id);
}
