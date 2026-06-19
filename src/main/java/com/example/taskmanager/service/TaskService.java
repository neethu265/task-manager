package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.exception.TaskException;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.validation.TaskValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    // CREATE++
    public TaskResponseDTO save(TaskRequestDTO request) {

        TaskValidation.validate(request);

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompletionPercentage(request.getCompletionPercentage());
        task.setAssignedDate(request.getAssignedDate());
        task.setDueDate(request.getDueDate());

        Task savedTask = repository.save(task);

        return mapToDTO(savedTask);
    }

    // READ ALL
    public List<TaskResponseDTO> getAll() {

        List<Task> tasks = repository.findAll();

        List<TaskResponseDTO> dtoList = new ArrayList<>();

        for(Task task : tasks) {
            dtoList.add(mapToDTO(task));
        }

        return dtoList;
    }

    // READ BY ID
    @Cacheable(value = "tasks", key = "#id")
    public TaskResponseDTO getById(Long id) {

        System.out.println("Fetching from Database...");

        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new TaskException("Task not found"));

        return mapToDTO(task);
    }

    // UPDATE
    @CachePut(value = "tasks", key = "#id")
    public TaskResponseDTO update(Long id,
                                  TaskRequestDTO request) {

        TaskValidation.validate(request);

        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new TaskException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompletionPercentage(
                request.getCompletionPercentage());
        task.setAssignedDate(
                request.getAssignedDate());
        task.setDueDate(
                request.getDueDate());

        Task updatedTask =
                repository.save(task);

        return mapToDTO(updatedTask);
    }

    // DELETE
    @CacheEvict(value = "tasks", key = "#id")
    public void delete(Long id) {

        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new TaskException("Task not found"));

        repository.delete(task);
    }
    public List<TaskResponseDTO>
    getByPriority(String priority) {

        List<Task> tasks =
                repository.findByPriority(priority);

        List<TaskResponseDTO> dtoList =
                new ArrayList<>();

        for(Task task : tasks) {
            dtoList.add(mapToDTO(task));
        }

        return dtoList;
    }
    public List<TaskResponseDTO>
    getAlmostCompletedTasks() {

        List<Task> tasks =
                repository.getAlmostCompletedTasks();

        List<TaskResponseDTO> dtoList =
                new ArrayList<>();

        for(Task task : tasks) {
            dtoList.add(mapToDTO(task));
        }

        return dtoList;
    }
    public List<TaskResponseDTO>
    getCompletedTasks() {

        List<Task> tasks =
                repository.getCompletedTasks();

        List<TaskResponseDTO> dtoList =
                new ArrayList<>();

        for(Task task : tasks) {
            dtoList.add(mapToDTO(task));
        }

        return dtoList;
    }

    // DTO Mapping Method
    private TaskResponseDTO mapToDTO(Task task) {

        TaskResponseDTO dto = new TaskResponseDTO();

        dto.setId(task.getId());
        dto.setAssignedDate(task.getAssignedDate());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority());
        dto.setCompletionPercentage(task.getCompletionPercentage());

        return dto;
    }
}