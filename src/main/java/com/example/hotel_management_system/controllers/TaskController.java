package com.example.hotel_management_system.controllers;

import com.example.hotel_management_system.DTO.TaskDTO;
import com.example.hotel_management_system.Services.HouseKeepingService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private HouseKeepingService housekeepingTaskService;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return housekeepingTaskService.getAllTasks();
    }

    @GetMapping("/employee/{employeeId}")
    public List<TaskDTO> getTasksByEmployee(@PathVariable Long employeeId) {
        return housekeepingTaskService.getTaskByEmployeeId(employeeId);
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO task) {
        return housekeepingTaskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        housekeepingTaskService.deleteTaskById(id);
    }
}
