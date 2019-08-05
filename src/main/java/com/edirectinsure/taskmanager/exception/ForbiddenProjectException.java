package com.edirectinsure.taskmanager.exception;

public class ForbiddenProjectException extends ForbiddenRequestException {
    public ForbiddenProjectException() {
        super("You has no permission to access this project");
    }
}
