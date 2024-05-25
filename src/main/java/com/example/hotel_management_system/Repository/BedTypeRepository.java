package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Bed_Type;
import com.example.hotel_management_system.Models.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BedTypeRepository extends JpaRepository<Bed_Type, Long> {
    Bed_Type findAllById(long id);

}

