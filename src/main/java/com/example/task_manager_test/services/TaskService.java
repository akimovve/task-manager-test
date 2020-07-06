package com.example.task_manager_test.services;

import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();

    void addTask(Task task);

    Task getTaskById(Long taskId) throws TaskNotFoundException;
}
