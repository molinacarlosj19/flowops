package com.flowops.workflowservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WorkflowNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleWorkflowNotFound(WorkflowNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", 404);
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> error = new HashMap<>();
        Map<String, String> fields = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> fields.put(fieldError.getField(), fieldError.getDefaultMessage()));

        error.put("timestamp", Instant.now());
        error.put("status", 400);
        error.put("error", "Bad Request");
        error.put("message", "Validation failed");
        error.put("fields", fields);

        return error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", 400);
        error.put("error", "Bad Request");
        error.put("message", ex.getMessage());
        return error;
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidEnum(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", Instant.now());
        error.put("status", 400);
        error.put("error", "Bad Request");
        error.put("message", "Invalid request body or enum value");
        return error;
    }
}