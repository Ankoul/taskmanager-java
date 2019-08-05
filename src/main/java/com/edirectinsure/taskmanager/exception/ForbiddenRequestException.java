package com.edirectinsure.taskmanager.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenRequestException extends ApplicationException {
    public ForbiddenRequestException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

}
