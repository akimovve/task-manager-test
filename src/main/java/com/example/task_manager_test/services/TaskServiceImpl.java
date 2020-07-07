package com.example.task_manager_test.services;

import com.example.task_manager_test.exceptions.AddTaskException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public <T, D> D addTask(T task, Function<Task, D> toDto, Function<T, Task> toTask)
            throws AddTaskException {
        Task convertedTask;
        try {
            convertedTask = toTask.apply(task);
            taskRepo.save(convertedTask);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new AddTaskException("Неправильный запрос при добавлении задачи.");
        }
        return toDto.apply(convertedTask);
    }

    @Override
    @Transactional
    public <T> T getList(Function<List<Task>, T> toDto) {
        List<Task> tasks = taskRepo.findAll();
        return toDto.apply(tasks);
    }

    @Override
    @Transactional
    public <T> T getTaskById(Long taskId, Function<Task, T> toDto)
            throws TaskNotFoundException {
        Task task = taskRepo.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(taskId + " - задача не найдена.");
        }
        return toDto.apply(task);
    }
}
