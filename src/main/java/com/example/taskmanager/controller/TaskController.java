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

    // CREATE
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody TaskRequestDTO request) {

        return ResponseEntity.ok(
                service.save(request));
    }
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponseDTO>>
    getTasksByPriority(
            @PathVariable String priority) {

        return ResponseEntity.ok(
                service.getByPriority(priority));
    }
    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponseDTO>>
    getCompletedTasks() {

        return ResponseEntity.ok(
                service.getCompletedTasks());
    }
    // READ ALL
    @GetMapping("/almost-complete")
    public ResponseEntity<List<TaskResponseDTO>>
    getAlmostCompletedTasks() {

        return ResponseEntity.ok(
                service.getAlmostCompletedTasks());
    }
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>>
    getAllTasks() {

        return ResponseEntity.ok(
                service.getAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>
    getTaskById(@PathVariable Long id) {

        return ResponseEntity.ok(
                service.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO>
    updateTask(@PathVariable Long id,
               @RequestBody TaskRequestDTO request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteTask(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Task deleted successfully");
    }
}