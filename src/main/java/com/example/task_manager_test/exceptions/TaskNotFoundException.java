package com.example.task_manager_test.exceptions;

public class TaskNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "No tasks in data base.";
    }

    @Override
    public String getLocalizedMessage() {
        return "Задач нет в базе данных.";
    }

    public static String getRID() {
        return "AE1244A80EA240CE89CE7A62A8B43ACB";
    }
}
