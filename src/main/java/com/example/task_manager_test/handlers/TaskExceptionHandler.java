package com.example.task_manager_test.handlers;

import com.example.task_manager_test.exceptions.TaskNotFoundException;
import com.example.task_manager_test.json.schemas.generated.TaskManagerError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleTaskException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, initException(ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private TaskManagerError initException(Exception ex) {
        TaskManagerError response = new TaskManagerError();
        response.setError(ex.getLocalizedMessage());
        response.setTimestamp(LocalDateTime.now());
        return response;
    }
}
