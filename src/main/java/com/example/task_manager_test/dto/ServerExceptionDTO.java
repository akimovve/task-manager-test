package com.example.task_manager_test.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class ServerExceptionDTO {

    @NotNull
    private String rid;

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    private String error;

    public ServerExceptionDTO(String rid, String error) {
        this.rid = rid;
        this.timestamp = LocalDateTime.now();
        this.error = error;
    }

    public ServerExceptionDTO() { }
}
