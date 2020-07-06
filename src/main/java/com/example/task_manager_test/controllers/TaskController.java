package com.example.task_manager_test.controllers;

import com.example.task_manager_test.dto.TaskDto;
import com.example.task_manager_test.exceptions.DataBaseNotCreatedException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-manager/api/v1")
public class TaskController {

    private final TaskServiceImpl taskService;

    @Autowired
    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() throws DataBaseNotCreatedException {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) throw new DataBaseNotCreatedException();
        return tasks;
    }

    @PutMapping("/add_task")
    public void addTask(@RequestBody TaskDto task) {
        taskService.addTask(new Task(task.getTaskType(), task.getUserId(), task.getStatus()));
    }

    @GetMapping("/tasks/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) throws TaskNotFoundException {
        Task task = taskService.getTaskById(taskId);
        if (task == null) throw new TaskNotFoundException();
        return task;
    }
}
