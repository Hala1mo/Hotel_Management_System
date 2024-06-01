package com.example.hotel_management_system.Repository;


import com.example.hotel_management_system.Models.Add_On;
import com.example.hotel_management_system.Models.Bed_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Add_OnRepository extends JpaRepository<Add_On, Long> {
    Add_On findAllById(long id);

}

