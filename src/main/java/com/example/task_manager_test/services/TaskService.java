package com.example.task_manager_test.services;

import com.example.task_manager_test.exceptions.AddTaskException;
import com.example.task_manager_test.exceptions.IncorrectEnterException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;

import java.util.List;
import java.util.function.Function;

public interface TaskService {

    <T, D> D addTask(T task, Function<Task, D> toDto)
            throws AddTaskException;

    /**
     * Function - функция перехода от одного объекта к другому
     */
    <T> T getList(Function<List<Task>, T> toDto);

    <T> T getTaskById(Long taskId, Function<Task, T> toDto) throws TaskNotFoundException;

    <T, D> T getTaskByTypeAndStatus(D request, Function<List<Task>, T> toDto)
            throws IncorrectEnterException;
}
