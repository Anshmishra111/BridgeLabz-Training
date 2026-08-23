package com.fundoo.fundoonotes.exception;

/**
 * DuplicateEmailException — thrown when a registration attempt uses
 * an email that already exists in the database.
 *
 * Mapped to HTTP 409 Conflict by GlobalExceptionHandler.
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
