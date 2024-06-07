package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.Services.HouseKeepingService;
import jakarta.validation.Valid;
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

    @GetMapping(value="id/{id}", params = "version=1")
    public TaskDTO getTasksByID(@PathVariable Long id) {
        return housekeepingTaskService.getTaskById(id);
    }

    @GetMapping(value="id/{id}", params = "version=2")
    public TaskDTO getTasksByIDV2(@PathVariable Long id) {
        return housekeepingTaskService.getTaskById(id);
    }
    @GetMapping("/employee/{employeeId}")
    public List<TaskDTO> getTasksByEmployee(@PathVariable Long employeeId) {
        return housekeepingTaskService.getTaskForEmployee(employeeId);
    }

    @PostMapping(value="/create")
    public TaskDTO createTask(@Valid @RequestBody CreateTaskDTO task) {
        return housekeepingTaskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        housekeepingTaskService.deleteTaskById(id);
    }

    @PutMapping("update/{id}")
    public void updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO task) {
        housekeepingTaskService.updateTask(id,task);
    }


    @GetMapping("status/finish/{id}")
    public void setTaskStatusAsFinished(@PathVariable Long id) {
         housekeepingTaskService.setTaskFinished(id);
    }

    @GetMapping("status/pending/{id}")
    public void setTaskStatusAsPending(@PathVariable Long id) {
            housekeepingTaskService.setTaskPending(id);
    }

    @GetMapping("status/underProgress/{id}")
    public void setTaskStatusAsUnderProgress(@PathVariable Long id) {
       housekeepingTaskService.setTaskInProgress(id);
    }


    @GetMapping("/status/underProgress")
    public void getTaskStatusUnderProgress() {
        housekeepingTaskService.getInProgressTasks();
    }

    @GetMapping("/status/completed")
    public void getTaskStatusCompleted() {
        housekeepingTaskService.getFinishedTasks();
    }
    @GetMapping("/status/pending")
    public void getTaskStatusPending() {
        housekeepingTaskService.getPendingTasks();
    }
}
