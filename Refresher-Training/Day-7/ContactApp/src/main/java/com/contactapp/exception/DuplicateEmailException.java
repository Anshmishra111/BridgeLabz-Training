package com.contactapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEmailException extends RuntimeException {

    private final String email;

    public DuplicateEmailException(String email) {
        super("A contact with email '" + email + "' already exists");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
