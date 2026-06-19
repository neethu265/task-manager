package com.example.taskmanager.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({
        "id",
        "assignedDate",
        "title",
        "description",
        "dueDate",
        "priority",
        "completionPercentage"
})
public class TaskResponseDTO {

    private Long id;

    private LocalDate assignedDate;

    private String title;

    private String description;

    private LocalDate dueDate;

    private String priority;

    private Integer completionPercentage;
}