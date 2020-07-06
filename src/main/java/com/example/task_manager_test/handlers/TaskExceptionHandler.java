package com.example.task_manager_test.handlers;

import com.example.task_manager_test.dto.ServerExceptionDTO;
import com.example.task_manager_test.exceptions.SQLDBNotCreatedException;
import com.example.task_manager_test.exceptions.TaskNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Object> handleTaskException(Exception ex, WebRequest request) {
        ServerExceptionDTO exceptionDTO = new ServerExceptionDTO(TaskNotFoundException.getRID(), ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(SQLDBNotCreatedException.class)
    public ResponseEntity<Object> handleDBNotCreatedSQLException(Exception ex, WebRequest request) {
        ServerExceptionDTO exceptionDTO = new ServerExceptionDTO(SQLDBNotCreatedException.getRID(), ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionDTO, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
