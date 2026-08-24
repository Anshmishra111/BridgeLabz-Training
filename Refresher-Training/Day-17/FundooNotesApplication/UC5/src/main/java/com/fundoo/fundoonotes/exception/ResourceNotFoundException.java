package com.fundoo.fundoonotes.exception;

/**
 * ResourceNotFoundException — thrown when a requested entity doesn't exist.
 * Mapped to HTTP 404 by GlobalExceptionHandler.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
