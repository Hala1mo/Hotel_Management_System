package com.example.hotel_management_system.Services.impl;

import com.example.hotel_management_system.DTO.TaskDTO;
import com.example.hotel_management_system.DTO.UserDTO;
import com.example.hotel_management_system.Mapper.UserMapper;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.HouseKeepingTask;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Models.User;
import com.example.hotel_management_system.Services.HouseKeepingService;
import com.example.hotel_management_system.repositories.EmployeeRepository;
import com.example.hotel_management_system.repositories.RoomRepository;
import com.example.hotel_management_system.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel_management_system.Mapper.TaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseKeepingServiceImpl implements HouseKeepingService {


    @Autowired
    TaskRepository taskRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    private RoomRepository roomRepository;

    TaskMapper taskMapper = new TaskMapper(employeeRepository, roomRepository);

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        HouseKeepingTask task = taskMapper.mapToEntity(taskDTO);
        HouseKeepingTask newTask = taskRepository.save(task);
        return taskMapper.mapToDTO(newTask);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<HouseKeepingTask> queryResult = taskRepository.findAll();
        List<TaskDTO> tasks = new ArrayList<>();
        for (HouseKeepingTask task : queryResult) {
            TaskDTO taskDTO = taskMapper.mapToDTO(task);
            tasks.add(taskDTO);
        }
        return tasks;
    }

    @Override
    public List<TaskDTO> getTaskByEmployeeId(long id) {

        return null;
    }

    @Override
    public TaskDTO updateTask(long id, TaskDTO taskDTO) {
        HouseKeepingTask task = taskRepository.findById(id);
        if(task!=null){

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
            task.setDescription(taskDTO.getDescription());
            task.setScheduledDate(taskDTO.getScheduledDate());
            task.setStatus(taskDTO.getStatus());
            HouseKeepingTask updatedTask = taskRepository.save(task);
            return taskMapper.mapToDTO(updatedTask);
        }
        return null;
    }



    @Override
    public void deleteTaskById(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
        taskRepository.delete(task);
    }
}
