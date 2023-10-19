package com.project.fxgames.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Record with %s not found";

    public DataNotFoundException(String id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}
