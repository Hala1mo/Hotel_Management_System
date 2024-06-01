package com.example.hotel_management_system.controllers;

import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.Services.HouseKeepingService;
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
    @GetMapping("id/{id}")
    public TaskDTO getTasksByID(@PathVariable Long id) {
        return housekeepingTaskService.getTaskById(id);
    }
    @GetMapping("/employee/{employeeId}")
    public List<TaskDTO> getTasksByEmployee(@PathVariable Long employeeId) {
        return housekeepingTaskService.getTaskByEmployee(employeeId);
    }

    @PostMapping("/create")
    public TaskDTO createTask(@RequestBody CreateTaskDTO task) {
        return housekeepingTaskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        housekeepingTaskService.deleteTaskById(id);
    }


    @GetMapping("Finish/{id}")
    public void setTaskStatusAsFinished(@PathVariable Long id) {
         housekeepingTaskService.setTaskFinished(id);
    }

    @GetMapping("Pending/{id}")
    public void setTaskStatusAsPending(@PathVariable Long id) {
            housekeepingTaskService.setTaskPending(id);
    }

    @GetMapping("UnderProgress/{id}")
    public void setTaskStatusAsUnderProgress(@PathVariable Long id) {
       housekeepingTaskService.setTaskInProgress(id);
    }


}
