package com.contactapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    private final Long contactId;

    public ContactNotFoundException(Long id) {
        super("Contact not found with id: " + id);
        this.contactId = id;
    }

    public Long getContactId() {
        return contactId;
    }
}
