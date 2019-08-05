package com.edirectinsure.taskmanager.exception;

public class UserAlreadyExistsException extends BadRequestException {
    public UserAlreadyExistsException(String username) {
        super("User " + username + " already exists.");
    }
}
