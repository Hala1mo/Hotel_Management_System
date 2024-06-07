package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Task.CreateTaskDTO;
import com.example.hotel_management_system.DTO.Task.TaskDTO;
import com.example.hotel_management_system.Models.Employee;
import com.example.hotel_management_system.Models.Enum.CleanlinessStatus;
import com.example.hotel_management_system.Models.Enum.TaskStatus;
import com.example.hotel_management_system.Models.HouseKeepingTask;
import com.example.hotel_management_system.Models.Room;
import com.example.hotel_management_system.Repository.RoomRepository;
import com.example.hotel_management_system.Services.HouseKeepingService;
import com.example.hotel_management_system.Repository.EmployeeRepository;
import com.example.hotel_management_system.Repository.TaskRepository;
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
    RoomRepository roomRepository;


    @Override
    public TaskDTO createTask(CreateTaskDTO taskDTO) {
        HouseKeepingTask task = new HouseKeepingTask();
        Optional<HouseKeepingTask> existingTask = taskRepository.findTaskByRoomAndDate(taskDTO.getRoom_id(), taskDTO.getScheduledDate());
        if (existingTask.isPresent()) {
            throw new IllegalArgumentException("Room is already assigned for cleaning on this date");
        }
        Optional<Employee> employeeOptional = employeeRepository.findById(taskDTO.getEmployee_id());
       Employee employee = employeeOptional.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id()));

        if(!employee.getDepartment().name().equals("HOUSEKEEPING")){
            throw new IllegalArgumentException("Employee is not in department HouseKeeping: ");
        }

        Optional<Room> roomOptional = roomRepository.findById(taskDTO.getRoom_id());
        Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + taskDTO.getRoom_id()));

        if(room.getCleanlinessStatus().equals("Clean")){
            throw new IllegalArgumentException("Room is already clean");
        }

        if(room.getCleanlinessStatus().equals("inProgress")){
            throw new IllegalArgumentException("Room is already in progress");
        }
        task.setDescription(taskDTO.getDescription());
        task.setEmployee(employee);
        task.setRoom(room);
        task.setStatus(TaskStatus.pending);
        task.setScheduledDate(taskDTO.getScheduledDate());
        HouseKeepingTask newTask = taskRepository.save(task);
        return TaskMapper.mapToDTO(newTask);
    }


    @Override
    public List<TaskDTO> getAllTasks() {
        List<HouseKeepingTask> queryResult = taskRepository.findAll();
        List<TaskDTO> tasks = new ArrayList<>();
        for (HouseKeepingTask task : queryResult) {
            TaskDTO taskDTO = TaskMapper.mapToDTO(task);
            tasks.add(taskDTO);
        }
        return tasks;
    }

    @Override
    public List<TaskDTO> getTaskForEmployee(long id) {
        Employee employee = employeeRepository.findById(id);
        if(employee!=null) {
            List<HouseKeepingTask> queryResult = taskRepository.findByEmployee(employee);
            List<TaskDTO> tasks = new ArrayList<>();
            if(!tasks.isEmpty()){
            for (HouseKeepingTask task : queryResult) {
                TaskDTO taskDTO = TaskMapper.mapToDTO(task);
                tasks.add(taskDTO);
            }
            return tasks;}
            else{
                throw new EntityNotFoundException("No tasks found for the employee with id: " + id);
            }
        }
        throw new EntityNotFoundException("Employee not found with id: " + id);
    }



    @Override
    public TaskDTO getTaskById(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
        if (task==null) {
            throw new EntityNotFoundException("No task found with this id");
        }
        return TaskMapper.mapToDTO(task);
    }

    @Override
    public TaskDTO getTaskByIdV2(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
        if (task==null) {
            throw new EntityNotFoundException("No task found with this id");
        }
        return TaskMapper.mapToDTOV2(task);
    }

    @Override
    public TaskDTO updateTask(long id, TaskDTO taskDTO) {
        HouseKeepingTask task = taskRepository.findById(id);
        if(task!=null){

            Optional<Employee> employeeOptional = employeeRepository.findById(taskDTO.getEmployee_id());
            Employee employee = employeeOptional.orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id()));
            task.setEmployee(employee);
            if (employee == null) {
                throw new EntityNotFoundException("Employee not found with id: " + taskDTO.getEmployee_id());}

            if(!employee.getDepartment().name().equals("HOUSEKEEPING")){
                throw new IllegalArgumentException("Employee is not in department HouseKeeping: ");
            }
          Room room = roomRepository.findAllById(taskDTO.getRoom_id());


            if (room == null) {
                throw new EntityNotFoundException("Room not found with id: " + taskDTO.getEmployee_id());
            }
            task.setRoom(room);
            task.setDescription(taskDTO.getDescription());
            task.setScheduledDate(taskDTO.getScheduledDate());
            task.setStatus(taskDTO.getStatus());
            HouseKeepingTask updatedTask = taskRepository.save(task);
            return TaskMapper.mapToDTO(updatedTask);
        }
        else{
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
    }



    @Override
    public void deleteTaskById(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
       if(task!=null)
       {
           taskRepository.delete(task);
       }
       else{
           throw new EntityNotFoundException("Task not found with id: " + id);
       }

    }

    @Override
    public void setTaskFinished(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
        if(task!=null){
            task.setStatus(TaskStatus.valueOf("completed"));
            taskRepository.save(task);

            Optional<Room> roomOptional=roomRepository.findById(task.getId());
            Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + task.getRoom().getId()));

            if (room != null) {
              room.setCleanlinessStatus(CleanlinessStatus.valueOf("Cleaned"));
                roomRepository.save(room);
            }
        }
        else{
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
    }

    @Override
    public void setTaskPending(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
        if(task!=null){
            task.setStatus(TaskStatus.valueOf("pending"));
            taskRepository.save(task);

            Optional<Room> roomOptional=roomRepository.findById(task.getId());
            Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + task.getRoom().getId()));

            if (room != null) {
                room.setCleanlinessStatus(CleanlinessStatus.valueOf("Dirty"));
                roomRepository.save(room);
            }
        }
        else{
            throw new EntityNotFoundException("Task not found with id: " + id);
        }


    }

    @Override
    public void setTaskInProgress(long id) {
        HouseKeepingTask task = taskRepository.findById(id);
        if(task!=null){
            task.setStatus(TaskStatus.valueOf("inProgress"));
            taskRepository.save(task);

            Optional<Room> roomOptional=roomRepository.findById(task.getId());
            Room room = roomOptional.orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + task.getRoom().getId()));

            if (room != null) {
                room.setCleanlinessStatus(CleanlinessStatus.valueOf("UnderMaintenance"));
                roomRepository.save(room);
            }
        }
        else{
            throw new EntityNotFoundException("Task not found with id: " + id);
        }

    }


    @Override
    public List<TaskDTO> getFinishedTasks() {
        List<HouseKeepingTask> queryResult = taskRepository.findAll();
        List<TaskDTO> tasks = new ArrayList<>();
        for (HouseKeepingTask task : queryResult) {
            if(task.getStatus().name().equals("completed")){
            TaskDTO taskDTO = TaskMapper.mapToDTO(task);
            tasks.add(taskDTO);}
        }
        return tasks;
    }

    @Override
    public List<TaskDTO> getInProgressTasks() {
        List<HouseKeepingTask> queryResult = taskRepository.findAll();
        List<TaskDTO> tasks = new ArrayList<>();
        for (HouseKeepingTask task : queryResult) {
            if(task.getStatus().name().equals("inProgress")){
                TaskDTO taskDTO = TaskMapper.mapToDTO(task);
                tasks.add(taskDTO);}
        }
        return tasks;
    }

    @Override
    public List<TaskDTO> getPendingTasks() {
        List<HouseKeepingTask> queryResult = taskRepository.findAll();
        List<TaskDTO> tasks = new ArrayList<>();
        for (HouseKeepingTask task : queryResult) {
            if(task.getStatus().name().equals("pending")){
                TaskDTO taskDTO = TaskMapper.mapToDTO(task);
                tasks.add(taskDTO);}
        }
        return tasks;
    }
 }

