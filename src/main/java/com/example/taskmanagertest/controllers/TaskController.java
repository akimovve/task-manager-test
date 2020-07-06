package com.example.taskmanagertest.controllers;

import com.example.taskmanagertest.dto.TaskDTO;
import com.example.taskmanagertest.exceptions.SQLDBNotCreatedException;
import com.example.taskmanagertest.exceptions.TaskNotFoundException;
import com.example.taskmanagertest.models.Task;
import com.example.taskmanagertest.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-manager/api/v1")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity getAllTasks() throws SQLDBNotCreatedException {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) throw new SQLDBNotCreatedException();
        return new ResponseEntity(tasks, HttpStatus.OK);
    }

    @PutMapping("/add_task")
    public ResponseEntity addTask(@RequestBody TaskDTO task) {
        taskService.addTask(new Task(task.getTaskType(), task.getUserId(), task.getStatus()));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity getTaskById(@PathVariable Long taskId) throws TaskNotFoundException {
        Task task = taskService.getTaskById(taskId);
        if (task == null) throw new TaskNotFoundException();
        return new ResponseEntity(task, HttpStatus.OK);
    }
}
