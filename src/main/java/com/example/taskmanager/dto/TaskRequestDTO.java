package com.example.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor


public class TaskRequestDTO {

    private String title;
    private String description;
    private String priority;
    private Integer completionPercentage;
    private LocalDate assignedDate;
    private LocalDate dueDate;

   }
