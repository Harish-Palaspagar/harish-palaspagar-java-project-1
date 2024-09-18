package com.xenosis.handler;

import com.xenosis.exception.DuplicateEmailException;
import com.xenosis.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * Intercepts exceptions thrown by controllers and returns appropriate HTTP responses.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions and returns a map of field errors.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : exception.getBindingResult()
                .getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);

    }

    /**
     * Handles exceptions when an employee is not found and returns a 404 response.
     */

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(EmployeeNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());

    }

    /**
     * Handles access denied exceptions and returns a forbidden response.
     */

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {

        return new ResponseEntity<>("You do not have access to perform this operation !!", HttpStatus.FORBIDDEN);

    }

    /**
     * Handles exceptions related to duplicate email addresses and returns a conflict response.
     */

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());

    }

    /**
     * Handles generic exceptions and returns a 500 internal server error response.
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred : " + exception.getMessage());
    }

}
