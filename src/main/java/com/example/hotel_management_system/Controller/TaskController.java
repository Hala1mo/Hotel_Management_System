package com.example.hotel_management_system.Controller;

import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTOV2;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Services.HouseKeepingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private HouseKeepingService housekeepingTaskService;

    @GetMapping
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks",
            content = @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks", content = @Content),
    })
    public List<TaskDTO> getAllTasks() {
        return housekeepingTaskService.getAllTasks();
    }

    @GetMapping(value="id/{id}", params = "version=1")
    @Operation(summary = "Get a task by ID (version 1)")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved task by ID",
            content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    public TaskDTO getTasksByID(@PathVariable Long id) {
        return housekeepingTaskService.getTaskById(id);
    }

    @GetMapping(value="id/{id}", params = "version=2")
    @Operation(summary = "Get a task by ID (version 2)")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved task by ID",
            content = @Content(schema = @Schema(implementation = TaskDTO.class)))
    public TaskDTOV2 getTasksByIDV2(@PathVariable Long id) {
        return housekeepingTaskService.getTaskByIdV2(id);
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get tasks by employee ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks for employee",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class))))
    public List<TaskDTO> getTasksByEmployee(@PathVariable Long employeeId) {
        return housekeepingTaskService.getTaskForEmployee(employeeId);
    }

    @PostMapping(value="/create")
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Successfully created task",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content)
    })
    public TaskDTO createTask(@Valid @RequestBody CreateTaskDTO task) {
        return housekeepingTaskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task by ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Successfully deleted task"),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks found with this id", content = @Content),
    })
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        housekeepingTaskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Update a task by ID")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully updated task",
            content = @Content(mediaType = "application/json",schema = @Schema(implementation = TaskDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks found with id", content = @Content),
    })

    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO task) {
        return ResponseEntity.ok(housekeepingTaskService.updateTask(id,task));
    }

    @GetMapping("status/finish/{id}")
    @Operation(summary = "Set task status as finished")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully updated task status to finished"),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks found with id", content = @Content),
    })

    public ResponseEntity<Void> setTaskStatusAsFinished(@PathVariable Long id) {
        housekeepingTaskService.setTaskFinished(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("status/pending/{id}")
    @Operation(summary = "Set task status as pending")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully updated task status to pending"),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No task found with this id", content = @Content),
    })

    public ResponseEntity<Void> setTaskStatusAsPending(@PathVariable Long id) {
        housekeepingTaskService.setTaskPending(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("status/underProgress/{id}")
    @Operation(summary = "Set task status as under progress")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully updated task status to under progress"),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks found with status pending", content = @Content),
    })

    public ResponseEntity<Void> setTaskStatusAsUnderProgress(@PathVariable Long id) {
        housekeepingTaskService.setTaskInProgress(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/underProgress")
    @Operation(summary = "Get tasks with status 'Under Progress'")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks with status 'Under Progress'",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks found with status pending", content = @Content),
    })
    public List<TaskDTO> getTaskStatusUnderProgress() {
        return housekeepingTaskService.getInProgressTasks();
    }

    @GetMapping("/status/completed")
    @Operation(summary = "Get tasks with status 'Completed'")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks with status 'Completed'",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)))),
    @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
    @ApiResponse(responseCode = "404", description = "No tasks found with status pending", content = @Content),
})
    public List<TaskDTO> getTaskStatusCompleted() {
        return housekeepingTaskService.getFinishedTasks();
    }

    @GetMapping("/status/pending")
    @Operation(summary = "Get tasks with status 'Pending'")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks with status 'Pending'",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TaskDTO.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized user", content = @Content),
            @ApiResponse(responseCode = "404", description = "No tasks found with status pending", content = @Content),
    })

    public List<TaskDTO> getTaskStatusPending() {
        return housekeepingTaskService.getPendingTasks();
    }
}
