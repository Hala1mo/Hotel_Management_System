package com.example.hotel_management_system.Services;


import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTOV2;

import java.util.List;

public interface HouseKeepingService {

    TaskDTO createTask(CreateTaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    List<TaskDTO> getTaskForEmployee(long id);
    TaskDTO getTaskById(long id);
    TaskDTOV2 getTaskByIdV2(long id);

    TaskDTO updateTask(long id, TaskDTO taskDTO);

    void deleteTaskById(long id);

    void setTaskFinished(long id);

    void setTaskPending(long id);
    void setTaskInProgress(long id);


    List<TaskDTO> getFinishedTasks();
    List<TaskDTO> getInProgressTasks();

    List<TaskDTO> getPendingTasks();
}
