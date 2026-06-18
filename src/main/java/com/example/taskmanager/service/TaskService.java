package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.validation.TaskValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    // CREATE
    public TaskResponseDTO save(
            TaskRequestDTO request) {

        TaskValidation.validate(request);

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompletionPercentage(
                request.getCompletionPercentage());
        task.setAssignedDate(
                request.getAssignedDate());
        task.setDueDate(
                request.getDueDate());

        Task savedTask =
                repository.save(task);

        TaskResponseDTO response =
                new TaskResponseDTO();

        response.setId(savedTask.getId());
        response.setTitle(savedTask.getTitle());
        response.setDescription(
                savedTask.getDescription());
        response.setPriority(
                savedTask.getPriority());
        response.setCompletionPercentage(
                savedTask.getCompletionPercentage());
        response.setAssignedDate(
                savedTask.getAssignedDate());
        response.setDueDate(
                savedTask.getDueDate());

        return response;
    }

    // READ ALL
    public List<TaskResponseDTO> getAll() {

        List<Task> tasks =
                repository.findAll();

        List<TaskResponseDTO> dtoList =
                new ArrayList<>();

        for(Task task : tasks) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(
                    task.getDescription());
            dto.setPriority(
                    task.getPriority());
            dto.setCompletionPercentage(
                    task.getCompletionPercentage());
            dto.setAssignedDate(
                    task.getAssignedDate());
            dto.setDueDate(
                    task.getDueDate());

            dtoList.add(dto);
        }

        return dtoList;
    }
}