package com.fundoo.fundoonotes.exception;

/**
 * InvalidNoteStateException — UC5
 * Thrown when trying to transition a note to an illegal state,
 * such as pinning a trashed/deleted note.
 *
 * Mapped to HTTP 400 Bad Request.
 */
public class InvalidNoteStateException extends RuntimeException {

    public InvalidNoteStateException(String message) {
        super(message);
    }
}
