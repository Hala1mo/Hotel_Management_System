package com.example.hotel_management_system.repositories;

import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.HouseKeepingTask;
import com.example.hotel_management_system.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<HouseKeepingTask, Long> {
    List<HouseKeepingTask> findAll();

    HouseKeepingTask findById(long id);
}
