package repositories;

import models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface customerRepository extends JpaRepository<Customer, Long> {
     List<Customer> findAll();
    Customer findById(long id);


}
