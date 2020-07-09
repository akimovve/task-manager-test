package com.example.task_manager_test.controllers;

import com.example.task_manager_test.exceptions.AddTaskException;
import com.example.task_manager_test.exceptions.IncorrectEnterException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.json.schemas.generated.*;
import com.example.task_manager_test.services.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@RestController
@RequestMapping("/task-manager/api/v1")
public class TaskController {

    private final TaskService taskService;
    private final DtoConverter dtoConverter;

    @Autowired
    public TaskController(TaskService taskService, DtoConverter dtoConverter) {
        this.taskService = taskService;
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("/tasks")
    @ApiOperation(value = "Получение списка всех задач.")
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_OK,
                    message = "OK",
                    response = TaskManagerList.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_FORBIDDEN,
                    message = "Forbidden",
                    response = TaskManagerError.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_BAD_REQUEST,
                    message = "Bad Request",
                    response = TaskManagerError.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "Internal Server Error",
                    response = TaskManagerError.class)
    })
    public TaskManagerList getAllTasks() {
        return taskService.getList(dtoConverter::toTaskListResponse);
    }

    @PostMapping("/add_task")
    @ApiOperation(value = "Добавить задачу.")
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_OK,
                    message = "OK",
                    response = TaskManagerAddItemResponse.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_FORBIDDEN,
                    message = "Forbidden",
                    response = AddTaskException.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_BAD_REQUEST,
                    message = "Bad Request",
                    response = AddTaskException.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "Internal Server Error",
                    response = AddTaskException.class)
    })
    public TaskManagerAddItemResponse addTask(
            @RequestBody TaskManagerItem newTask)
            throws AddTaskException {
        return taskService.addTask(newTask,
                dtoConverter::toTaskAddItemResponse);
    }

    @GetMapping("/tasks/{taskId}")
    @ApiOperation(value = "Получение задачи по её индексу.")
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_OK,
                    message = "OK",
                    response = TaskManagerList.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_FORBIDDEN,
                    message = "Forbidden",
                    response = TaskManagerError.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_BAD_REQUEST,
                    message = "Bad Request",
                    response = TaskManagerError.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "Internal Server Error",
                    response = TaskManagerError.class)
    })
    public TaskManagerItem getTaskById(@PathVariable Long taskId)
            throws TaskNotFoundException {
        return taskService.getTaskById(taskId,
                dtoConverter::toTaskItemResponse);
    }

    @PostMapping("/get_by_type_and_status")
    @ApiOperation(value = "Получить задачу по её типу и статусу.")
    @ApiResponses(value = {
            @ApiResponse(
                    code = HttpURLConnection.HTTP_OK,
                    message = "OK",
                    response = TaskManagerList.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_FORBIDDEN,
                    message = "Forbidden",
                    response = IncorrectEnterException.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_BAD_REQUEST,
                    message = "Bad Request",
                    response = IncorrectEnterException.class),
            @ApiResponse(
                    code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                    message = "Internal Server Error",
                    response = IncorrectEnterException.class)
    })
    public TaskManagerList getTaskByTypeAndStatus(
            @RequestBody TaskManagerFindByTypeAndStatusItem item)
            throws IncorrectEnterException {
        return taskService.getTaskByTypeAndStatus(item,
                dtoConverter::toTaskListResponse);
    }
}
