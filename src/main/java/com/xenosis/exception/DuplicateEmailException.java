package com.xenosis.exception;

/**
 * Exception thrown when a duplicate email is detected.
 */

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }

}
