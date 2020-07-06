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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm dd.MM.yyyy")
    private LocalDateTime createdAt;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public void processing() {
        if (status != Status.AWAITING)
            throw new IllegalStateException(status.toString());
        status = Status.PROCESSING;
    }

    public void finished() {
        if (status != Status.PROCESSING)
            throw new IllegalStateException(status.toString());
        status = Status.DONE;
    }

    public void failed() {
        if (status != Status.PROCESSING)
            throw new IllegalStateException(status.toString());
        status = Status.FAILED;
    }

    public Task(@NotNull TaskType taskType, @NotNull Long userId, @NotNull Status status) {
        this.taskType = taskType;
        this.userId = userId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Task() {
    }
}
