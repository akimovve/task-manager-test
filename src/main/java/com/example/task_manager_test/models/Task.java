package com.example.task_manager_test.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Data
public class Task {

    public enum Status {
        AWAITING,
        PROCESSING,
        DONE,
        FAILED
    }

    public enum TaskType {
        SHARE_RATES,
        DELETE_RATE,
        EDIT_RATE,
        OPEN_RATE
    }

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Column(nullable = false, updatable = false)
    private Long userId;

    @Column(nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(@NotNull TaskType taskType, @NotNull Long userId, @NotNull Status status) {
        this.taskType = taskType;
        this.userId = userId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Task(@NotNull TaskType taskType, @NotNull Status status) {
        this.taskType = taskType;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Task() { }
}
