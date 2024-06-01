package com.example.hotel_management_system.Services;


import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;

import java.util.List;

public interface HouseKeepingService {

    TaskDTO createTask(CreateTaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    List<TaskDTO> getTaskByEmployee(long id);
    TaskDTO getTaskById(long id);

    TaskDTO updateTask(long id, TaskDTO taskDTO);

    void deleteTaskById(long id);

    void setTaskFinished(long id);

    void setTaskPending(long id);
    void setTaskInProgress(long id);


}
