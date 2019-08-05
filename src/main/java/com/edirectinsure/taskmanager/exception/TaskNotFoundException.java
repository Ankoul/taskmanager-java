package com.edirectinsure.taskmanager.exception;

public class TaskNotFoundException extends BadRequestException {
    public TaskNotFoundException(Long taskId) {
        super("Task " + taskId + " not found.");
    }
}
