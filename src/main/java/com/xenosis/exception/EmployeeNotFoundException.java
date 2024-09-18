package com.xenosis.exception;

/**
 * Exception thrown when an employee is not found.
 */

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }

}