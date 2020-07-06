package com.example.taskmanagertest.services;

import com.example.taskmanagertest.models.Task;
import com.example.taskmanagertest.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public void addTask(Task task) {
        taskRepo.save(task);
    }

    public Task getTaskById(Long taskId) {
        return taskRepo.getTaskById(taskId);
    }
}
