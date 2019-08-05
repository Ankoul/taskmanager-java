package com.edirectinsure.taskmanager.exception;

public class ProjectNotFoundException extends BadRequestException {
    public ProjectNotFoundException(Long taskId) {
        super("Project " + taskId + " not found.");
    }
}
