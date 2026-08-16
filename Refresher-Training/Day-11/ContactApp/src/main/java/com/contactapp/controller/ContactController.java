package com.contactapp.controller;

import com.contactapp.dto.request.ContactRequestDTO;
import com.contactapp.dto.response.ContactResponseDTO;
import com.contactapp.service.IContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@Tag(name = "Contacts", description = "CRUD operations for managing contacts")
public class ContactController {

    private final IContactService contactService;

    @Operation(summary = "Create a new contact", description = "Creates a contact and returns the saved record with its ID and timestamps.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contact created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error — check request body"),
            @ApiResponse(responseCode = "409", description = "A contact with this email already exists")
    })
    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(
            @Valid @RequestBody ContactRequestDTO dto) {
        ContactResponseDTO created = contactService.createContact(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all contacts", description = "Returns a list of all contacts (empty list if none exist).")
    @ApiResponse(responseCode = "200", description = "List returned successfully")
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @Operation(summary = "Get a contact by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact found"),
            @ApiResponse(responseCode = "404", description = "Contact not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(
            @Parameter(description = "ID of the contact to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @Operation(summary = "Search contacts by name (case-insensitive, partial match)")
    @ApiResponse(responseCode = "200", description = "Search results returned")
    @GetMapping("/search")
    public ResponseEntity<List<ContactResponseDTO>> searchByName(
            @Parameter(description = "Name substring to search for") @RequestParam String name) {
        return ResponseEntity.ok(contactService.searchByName(name));
    }

    @Operation(summary = "Update an existing contact")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "409", description = "Email already used by another contact")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @PathVariable Long id,
            @Valid @RequestBody ContactRequestDTO dto) {
        return ResponseEntity.ok(contactService.updateContact(id, dto));
    }

    @Operation(summary = "Delete a contact by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contact deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Contact not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all contacts", description = "Permanently removes every contact from the database.")
    @ApiResponse(responseCode = "204", description = "All contacts deleted")
    @DeleteMapping
    public ResponseEntity<Void> deleteAllContacts() {
        contactService.deleteAllContacts();
        return ResponseEntity.noContent().build();
    }
}
