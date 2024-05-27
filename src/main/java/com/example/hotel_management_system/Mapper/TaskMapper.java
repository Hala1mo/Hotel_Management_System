package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.TaskDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.HouseKeepingTask;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.repositories.EmployeeRepository;
import com.example.hotel_management_system.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TaskMapper {


    private final EmployeeRepository employeeRepository;
    private final RoomRepository roomRepository;

    public TaskMapper(EmployeeRepository employeeRepository,RoomRepository roomRepository) {
        this.employeeRepository = employeeRepository;
        this.roomRepository=roomRepository;
    }

    public  TaskDTO mapToDTO(HouseKeepingTask task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setRoom_id(task.getId());
        taskDTO.setEmployee_id(task.getAssignedEmployee().getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setScheduledDate(task.getScheduledDate());
        return taskDTO;
    }

    // convert DTO to entity
    public HouseKeepingTask mapToEntity(TaskDTO taskDTO) {
        HouseKeepingTask task = new HouseKeepingTask();
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());

        task.setScheduledDate(taskDTO.getScheduledDate());
        Optional<Employee> employeeOptional = employeeRepository.findById(taskDTO.getEmployee_id());
        Employee employee = employeeOptional.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id()));
        task.setAssignedEmployee(employee);
        if (employee == null) {
            throw new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id());
        }

        Optional<Room> roomOptional = roomRepository.findById(taskDTO.getRoom_id());
        Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + taskDTO.getRoom_id()));
        task.setRoom(room);
        if (room == null) {
            throw new EntityNotFoundException("Room not found with id: " + taskDTO.getEmployee_id());
        }







        return task;
    }
}
