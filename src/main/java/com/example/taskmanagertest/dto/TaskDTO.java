package com.example.taskmanagertest.dto;

import com.example.taskmanagertest.models.Status;
import com.example.taskmanagertest.models.TaskType;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TaskDTO {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @NotNull
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public TaskType getTaskType() {
        return taskType;
    }

    public Long getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }
}
