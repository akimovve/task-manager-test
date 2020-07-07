package com.example.task_manager_test.services;

import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public void addTask(Task task) {
        taskRepo.save(task);
    }

    @Override
    public <T> T getList(Function<List<Task>, T> toDto) {
        List<Task> tasks = taskRepo.findAll();
        return toDto.apply(tasks);
    }

    @Override
    public <T> T getTaskById(Long taskId, Function<Task, T> toDto) throws TaskNotFoundException {
        Task task = taskRepo
                .findById(taskId)
                .orElseThrow(TaskNotFoundException::new);
        return toDto.apply(task);
    }
}
