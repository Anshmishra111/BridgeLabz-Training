package com.contactapp.controller;

import com.contactapp.model.Contact;
import com.contactapp.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    // -------------------------------------------------------
    // CREATE  ->  POST /api/contacts
    // -------------------------------------------------------
    @PostMapping
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) {
        Contact saved = contactService.saveContact(contact);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // -------------------------------------------------------
    // READ ALL  ->  GET /api/contacts
    // -------------------------------------------------------
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    // -------------------------------------------------------
    // READ BY ID  ->  GET /api/contacts/{id}
    // -------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Contact not found with id: " + id);
    }

    // -------------------------------------------------------
    // SEARCH BY NAME  ->  GET /api/contacts/search?name=John
    // -------------------------------------------------------
    @GetMapping("/search")
    public ResponseEntity<List<Contact>> searchByName(@RequestParam String name) {
        List<Contact> contacts = contactService.searchByName(name);
        return ResponseEntity.ok(contacts);
    }

    // -------------------------------------------------------
    // UPDATE  ->  PUT /api/contacts/{id}
    // -------------------------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id,
                                           @Valid @RequestBody Contact contact) {
        try {
            Contact updated = contactService.updateContact(id, contact);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // -------------------------------------------------------
    // DELETE BY ID  ->  DELETE /api/contacts/{id}
    // -------------------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        try {
            String message = contactService.deleteContact(id);
            return ResponseEntity.ok(message);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // -------------------------------------------------------
    // DELETE ALL  ->  DELETE /api/contacts
    // -------------------------------------------------------
    @DeleteMapping
    public ResponseEntity<String> deleteAllContacts() {
        String message = contactService.deleteAllContacts();
        return ResponseEntity.ok(message);
    }
}
