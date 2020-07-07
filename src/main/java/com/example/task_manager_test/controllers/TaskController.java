package com.example.task_manager_test.controllers;

import com.example.task_manager_test.exceptions.DataBaseNotCreatedException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.json.schemas.generated.TaskManagerItem;
import com.example.task_manager_test.json.schemas.generated.TaskManagerList;
import com.example.task_manager_test.models.Task;
import com.example.task_manager_test.services.TaskServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@RestController
@RequestMapping("/task-manager/api/v1")
public class TaskController {

    private final TaskServiceImpl taskService;
    private final DtoConverter dtoConverter;

    @Autowired
    public TaskController(TaskServiceImpl taskService, DtoConverter dtoConverter) {
        this.taskService = taskService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("/tasks")
    @ApiOperation(value = "Получение списка всех задач.")
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_OK,
                    message = "OK",
                    response = TaskManagerList.class)
    })
    public TaskManagerList getAllTasks() throws DataBaseNotCreatedException {
        return taskService.getList(dtoConverter::toTaskListResponse);
    }

    @PutMapping("/add_task")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(new Task(task.getTaskType(), task.getUserId(), task.getStatus()));
    }

    @GetMapping("/tasks/{taskId}")
    public TaskManagerItem getTaskById(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskService.getTaskById(taskId, dtoConverter::toTaskItemResponse);
    }
}
