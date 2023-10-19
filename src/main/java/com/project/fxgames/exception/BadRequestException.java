package com.project.fxgames.exception;

public class BadRequestException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Record with id = %s already exists";

    public BadRequestException(String id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}