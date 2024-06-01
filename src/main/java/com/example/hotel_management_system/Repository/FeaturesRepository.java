package com.example.hotel_management_system.Repository;

import com.example.hotel_management_system.Models.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FeaturesRepository extends JpaRepository<Features, Long> {
    Features findAllById(long id);

}
