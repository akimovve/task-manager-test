package com.example.task_manager_test.controllers;

import com.example.task_manager_test.models.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public String toTaskType(Task.TaskType taskType) {
        return taskType.name().toLowerCase();
    }

    public Task.TaskType toTaskType(String taskType) {
        return Task.TaskType.valueOf(taskType);
    }

    public String toStatus(Task.Status status) {
        return status.name().toUpperCase();
    }

    public Task.Status toStatus(String status) {
        return Task.Status.valueOf(status);
    }
}
