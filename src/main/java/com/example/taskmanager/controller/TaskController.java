package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService service;

    @PostMapping
    public ResponseEntity<TaskResponseDTO>
    createTask(
            @RequestBody TaskRequestDTO request) {

        return ResponseEntity.ok(
                service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>>
    getAllTasks()
    {

        return ResponseEntity.ok(
                service.getAll());
    }
}