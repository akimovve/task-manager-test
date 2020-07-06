package com.example.taskmanagertest.repos;

import com.example.taskmanagertest.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
    Task getTaskById(Long id);
}
