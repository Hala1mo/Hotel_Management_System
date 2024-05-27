package com.example.hotel_management_system.Services;


import com.example.hotel_management_system.DTO.TaskDTO;

import java.util.List;

public interface HouseKeepingService {
    TaskDTO createTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    List<TaskDTO> getTaskByEmployeeId(long id);

    TaskDTO updateTask(long id, TaskDTO taskDTO);

    void deleteTaskById(long id);


}
