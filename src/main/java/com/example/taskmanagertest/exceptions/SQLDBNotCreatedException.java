package com.example.taskmanagertest.exceptions;

import java.sql.SQLException;

public class SQLDBNotCreatedException extends SQLException {

    @Override
    public String getMessage() {
        return "Internal Server Error.";
    }

    @Override
    public String getLocalizedMessage() {
        return "Произошла внутренняя ошибка сервера.";
    }

    public static String getRID() {
        return "AE1244A80EA240CE89CE7A62A8B43ACB";
    }
}
