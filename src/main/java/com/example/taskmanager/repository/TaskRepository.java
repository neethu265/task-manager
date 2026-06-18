package com.example.taskmanager.repository;

import com.example.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    // Find tasks by priority
    List<Task> findByPriority(String priority);

    // Almost completed tasks
    @Query("""
            SELECT t
            FROM Task t
            WHERE t.completionPercentage >= 80
            AND t.completionPercentage < 100
            """)
    List<Task> getAlmostCompletedTasks();

    // Completed tasks
    @Query("""
            SELECT t
            FROM Task t
            WHERE t.completionPercentage = 100
            """)
    List<Task> getCompletedTasks();
}