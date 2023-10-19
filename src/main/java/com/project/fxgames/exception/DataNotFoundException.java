package com.project.fxgames.exception;


public class DataNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Record with id = %s not found";

    public DataNotFoundException(String id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}
