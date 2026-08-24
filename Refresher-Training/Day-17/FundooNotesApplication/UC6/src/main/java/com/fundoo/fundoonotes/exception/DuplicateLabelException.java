package com.fundoo.fundoonotes.exception;

/**
 * DuplicateLabelException — UC6
 *
 * Thrown when a user tries to create or rename a label to a name
 * they already have as an active label.
 *
 * Uniqueness is per-user: two different users CAN have a "Work" label,
 * but one user CANNOT have two active "Work" labels.
 *
 * Mapped to HTTP 409 Conflict by GlobalExceptionHandler.
 */
public class DuplicateLabelException extends RuntimeException {

    public DuplicateLabelException(String message) {
        super(message);
    }
}
