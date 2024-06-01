package com.example.hotel_management_system.repositories;

import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.HouseKeepingTask;
import com.example.hotel_management_system.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<HouseKeepingTask, Long> {
    List<HouseKeepingTask> findAll();

    HouseKeepingTask findById(long id);

    List<HouseKeepingTask> findByEmployee(Employee employee);

    @Query("SELECT t FROM HouseKeepingTask t WHERE t.room.id = :roomId AND t.scheduledDate = :date")
    Optional<HouseKeepingTask> findTaskByRoomAndDate(@Param("roomId") Long roomId, @Param("date") LocalDate date);

}
