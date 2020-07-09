package com.example.task_manager_test.services;

import com.example.task_manager_test.annotations.TransactionRequiresNew;
import com.example.task_manager_test.annotations.TransactionRequired;
import com.example.task_manager_test.exceptions.AddTaskException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
    @TransactionRequiresNew
    public <T, D> D addTask(T task, Function<Task, D> toDto, Function<T, Task> toTask)
            throws AddTaskException {
        Task convertedTask;
        System.out.println("Transaction active? : " + TransactionSynchronizationManager.isActualTransactionActive());
        try {
            convertedTask = toTask.apply(task);
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
        System.out.println("Transaction active? : " + TransactionSynchronizationManager.isActualTransactionActive());
        return toDto.apply(tasks);
    }

    @Override
    @TransactionRequired
    public <T> T getTaskById(Long taskId, Function<Task, T> toDto)
            throws TaskNotFoundException {
        Task task = taskRepo.findById(taskId).orElse(null);
        System.out.println("Transaction active? : " + TransactionSynchronizationManager.isActualTransactionActive());
        if (task == null) {
            throw new TaskNotFoundException(taskId + " - задача не найдена.");
        }
        return toDto.apply(task);
    }
}
