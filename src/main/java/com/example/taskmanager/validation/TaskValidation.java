package com.example.taskmanager.validation;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.exception.TaskException;

public class TaskValidation {

    public static void validate(
            TaskRequestDTO request) {

        if(request.getTitle()==null ||
                request.getTitle().trim().isEmpty()) {

            throw new TaskException(
                    "Title cannot be empty");
        }

        if(request.getPriority()==null ||
                request.getPriority().trim().isEmpty()) {

            throw new TaskException(
                    "Priority cannot be empty");
        }

        if(request.getCompletionPercentage()==null) {

            throw new TaskException(
                    "Completion percentage is required");
        }

        if(request.getCompletionPercentage()<0 ||
                request.getCompletionPercentage()>100) {

            throw new TaskException(
                    "Completion percentage must be between 0 and 100");
        }

        if(request.getAssignedDate()==null) {

            throw new TaskException(
                    "Assigned date is required");
        }

        if(request.getDueDate()==null) {

            throw new TaskException(
                    "Due date is required");
        }

        if(request.getDueDate()
                .isBefore(request.getAssignedDate())) {

            throw new TaskException(
                    "Due date cannot be before assigned date");
        }
    }
}