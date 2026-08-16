package com.contactapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    private final Long contactId;

    public ContactNotFoundException(Long id) {
        super("Contact not found with id: " + id);
        this.contactId = id;
    }
}
