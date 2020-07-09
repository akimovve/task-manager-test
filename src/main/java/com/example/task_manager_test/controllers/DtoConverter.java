package com.example.task_manager_test.controllers;

import com.example.task_manager_test.json.schemas.generated.TaskManagerAddItemResponse;
import com.example.task_manager_test.json.schemas.generated.TaskManagerFindByTypeAndStatusItem;
import com.example.task_manager_test.json.schemas.generated.TaskManagerItem;
import com.example.task_manager_test.json.schemas.generated.TaskManagerList;
import com.example.task_manager_test.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConverter {

    private final TaskConverter taskConverter;

    @Autowired
    public DtoConverter(TaskConverter taskConverter) {
        this.taskConverter = taskConverter;
    }

    public TaskManagerItem toTaskItemResponse(Task task) {
        TaskManagerItem response = new TaskManagerItem();
        response.setId(task.getId());
        response.setTaskType(taskConverter.toTaskType(task.getTaskType()));
        response.setCreatedAt(task.getCreatedAt());
        response.setStatus(taskConverter.toStatus(task.getStatus()));
        return response;
    }

    public TaskManagerList toTaskListResponse(List<Task> tasks) {
        TaskManagerList response = new TaskManagerList();
        response.setItems(tasks
                .stream()
                .map(this::toTaskItemResponse)
                .collect(Collectors.toList()));
        return response;
    }

    public TaskManagerAddItemResponse toTaskAddItemResponse(Task task) {
        TaskManagerAddItemResponse response = new TaskManagerAddItemResponse();
        response.setId(task.getId());
        response.setStatus(taskConverter.toStatus(task.getStatus()));
        return response;
    }

    public Task toTask(TaskManagerItem item) {
        return new Task(
                taskConverter.toTaskType(item.getTaskType()),
                item.getUserId(),
                taskConverter.toStatus(item.getStatus()));
    }

    public Task toTask(TaskManagerFindByTypeAndStatusItem item) {
        return new Task(
                taskConverter.toTaskType(item.getTaskType()),
                taskConverter.toStatus(item.getStatus())
        );
    }
}
