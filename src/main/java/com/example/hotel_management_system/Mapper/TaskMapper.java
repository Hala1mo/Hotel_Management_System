package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTOV2;
import com.example.hotel_management_system.Models.HouseKeepingTask;

public class TaskMapper {

    public static  TaskDTO mapToDTO(HouseKeepingTask task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setRoom_id(task.getRoom().getId());
        taskDTO.setEmployee_id(task.getEmployee().getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setScheduledDate(task.getScheduledDate());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }

    public static  TaskDTOV2 mapToDTOV2(HouseKeepingTask task) {
        TaskDTOV2 taskDTO = new TaskDTOV2();
        taskDTO.setId(task.getId());
        taskDTO.setRoom_id(task.getRoom().getId());
        taskDTO.setEmployee_id(task.getEmployee().getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setScheduledDate(task.getScheduledDate());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setFloor_number(task.getRoom().getFloor_number());
        return taskDTO;
    }

}
