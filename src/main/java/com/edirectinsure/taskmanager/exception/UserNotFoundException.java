package com.edirectinsure.taskmanager.exception;

public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException(String username) {
        super("User " + username + " not found.");
    }
}
