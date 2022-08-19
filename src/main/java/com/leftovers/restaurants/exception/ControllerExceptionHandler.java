package com.leftovers.restaurants.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public Map<String, Object> handleNoSuchElementExceptions(NoSuchElementException ex) {
        log.error(ex.getMessage());
        return response(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> response(String errorMsg, HttpStatus status) {
        var response = new HashMap<String, Object>();
        response.put("error", errorMsg);
        response.put("status", status.value());
        return response;
    }
}