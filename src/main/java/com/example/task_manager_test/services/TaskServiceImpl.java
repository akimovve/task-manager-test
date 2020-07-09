package com.example.task_manager_test.services;

import com.example.task_manager_test.annotations.TransactionRequired;
import com.example.task_manager_test.exceptions.AddTaskException;
import com.example.task_manager_test.exceptions.IncorrectEnterException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.json.schemas.generated.TaskManagerFindByTypeAndStatusItem;
import com.example.task_manager_test.json.schemas.generated.TaskManagerItem;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @TransactionRequired
    public <T, D> D addTask(T task, Function<Task, D> toDto)
            throws AddTaskException {
        Task convertedTask;
        try {
            TaskManagerItem item = (TaskManagerItem) task;
            convertedTask = new Task(
                    Task.TaskType.valueOf(item.getTaskType()),
                    item.getUserId(),
                    Task.Status.valueOf(item.getStatus()));
            taskRepo.save(convertedTask);
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new AddTaskException("Неправильный запрос при добавлении задачи.");
        }
        return toDto.apply(convertedTask);
    }

    @Override
    @TransactionRequired
    public <T> T getList(Function<List<Task>, T> toDto) {
        List<Task> tasks = taskRepo.findAll();
        return toDto.apply(tasks);
    }

    @Override
    @TransactionRequired
    public <T> T getTaskById(Long taskId, Function<Task, T> toDto)
            throws TaskNotFoundException {
        Task task = taskRepo.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(taskId + " - задача не найдена.");
        }
        return toDto.apply(task);
    }

    @Override
    @TransactionRequired
    public <T, D> T getTaskByTypeAndStatus(D request, Function<List<Task>, T> toDto)
            throws IncorrectEnterException {
        List<Task> tasks;
        try {
            TaskManagerFindByTypeAndStatusItem item = (TaskManagerFindByTypeAndStatusItem) request;
            tasks = taskRepo.findByTaskTypeAndStatus(
                    Task.TaskType.valueOf(item.getTaskType()),
                    Task.Status.valueOf(item.getStatus()));
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IncorrectEnterException("Неправильный запрос при поиске по типу и значению.");
        }
        return toDto.apply(tasks);
    }

}
