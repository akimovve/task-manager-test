package com.example.task_manager_test.services;

import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface TaskService {

    void addTask(Task task);

    /**
     * Function - функция перехода от одного объекта к другому
     */
    <T> T getList(Function<List<Task>, T> toDto);

    <T> T getTaskById(Long taskId, Function<Task, T> toDto) throws TaskNotFoundException;
}
