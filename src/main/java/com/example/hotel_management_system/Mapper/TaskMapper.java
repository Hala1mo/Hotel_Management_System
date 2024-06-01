package com.example.hotel_management_system.Mapper;


import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.Models.HouseKeepingTask;

public class TaskMapper {

    public static  TaskDTO mapToDTO(HouseKeepingTask task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setRoom_id(task.getRoom().getId());
        taskDTO.setEmployee_id(task.getEmployee().getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setScheduledDate(task.getScheduledDate());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }

    // convert DTO to entity
//    public static HouseKeepingTask mapToEntity(TaskDTO taskDTO) {
//        HouseKeepingTask task = new HouseKeepingTask();
//        task.setDescription(taskDTO.getDescription());
//        task.setStatus(taskDTO.getStatus());
//        task.setEmployee(taskDTO);
//        task.setRoom(room);
//        task.setScheduledDate(taskDTO.getScheduledDate());
//        Optional<Employee> employeeOptional = employeeRepository.findById(taskDTO.getEmployee_id());
//        Employee employee = employeeOptional.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id()));
//
//        if (employee == null) {
//            throw new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id());
//        }
//
//        if(!employee.getDepartment().name().equals("HOUSEKEEPING")){
//            throw new InvalidDepartmentException("Employee is not in department HouseKeeping: ");
//        }
//
//        Optional<Room> roomOptional = roomRepository.findById(taskDTO.getRoom_id());
//        Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + taskDTO.getRoom_id()));
//        task.setRoom(room);
//        if (room == null) {
//            throw new EntityNotFoundException("Room not found with id: " + taskDTO.getEmployee_id());
//        }

       // return task;
    //}

//    public static HouseKeepingTask createTask(CreateTaskDTO taskDTO) {
//        HouseKeepingTask task = new HouseKeepingTask();
//        Optional<Employee> employeeOptional = employeeRepository.findById(taskDTO.getEmployee_id());
//        Employee employee = employeeOptional.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id()));
//        task.setEmployee(employee);
//        if (employee == null) {
//            throw new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id());
//        }
//
//        if(!employee.getDepartment().name().equals("HOUSEKEEPING")){
//            throw new IllegalArgumentException("Employee is not in department HouseKeeping: ");
//        }
//
//        Optional<Room> roomOptional = roomRepository.findById(taskDTO.getRoom_id());
//        Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + taskDTO.getRoom_id()));
//        task.setRoom(room);
//        if (room == null) {
//            throw new EntityNotFoundException("Room not found with id: " + taskDTO.getEmployee_id());
//        }
//
//        if(room.getCleanlinessStatus().equals("Clean")){
//            throw new IllegalArgumentException("Room is already clean");
//        }
//
//        if(room.getCleanlinessStatus().equals("inProgress")){
//            throw new IllegalArgumentException("Room is already in progress");
//        }
//        task.setDescription(taskDTO.getDescription());
//        task.setStatus(TaskStatus.pending);
//        task.setScheduledDate(taskDTO.getScheduledDate());
//
//
//
//        return task;
//    }
}
