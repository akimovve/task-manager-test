package com.example.taskmanagertest.handlers;

import com.example.taskmanagertest.dto.ServerExceptionDTO;
import com.example.taskmanagertest.exceptions.SQLDBNotCreatedException;
import com.example.taskmanagertest.exceptions.TaskNotFoundException;
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
    public ResponseEntity handleTaskException(Exception ex, WebRequest request) {
        ServerExceptionDTO exceptionDTO = new ServerExceptionDTO(TaskNotFoundException.getRID(), ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(SQLDBNotCreatedException.class)
    public ResponseEntity handleDBNotCreatedSQLException(Exception ex, WebRequest request) {
        ServerExceptionDTO exceptionDTO = new ServerExceptionDTO(SQLDBNotCreatedException.getRID(), ex.getLocalizedMessage());
        return handleExceptionInternal(ex, exceptionDTO, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
