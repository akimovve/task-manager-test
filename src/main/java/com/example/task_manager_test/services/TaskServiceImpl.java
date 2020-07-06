package com.example.task_manager_test.services;

import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        taskRepo.save(task);
    }

    @Override
    @Transactional
    public Task getTaskById(Long taskId) throws TaskNotFoundException {
        return taskRepo
                .findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
    }
}
