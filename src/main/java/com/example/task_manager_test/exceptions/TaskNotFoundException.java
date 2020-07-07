package com.example.task_manager_test.exceptions;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String message) {
        super(message);
    }
}
