package com.contactapp.service;

import com.contactapp.dto.request.ContactRequestDTO;
import com.contactapp.dto.response.ContactResponseDTO;

import java.util.List;

/**
 * Service contract for contact management operations.
 * Controllers depend on this interface, not the implementation,
 * enabling easy mocking in tests and future swapping of implementations.
 */
public interface IContactService {

    /**
     * Creates and persists a new contact.
     *
     * @param dto the contact data from the request body
     * @return the saved contact as a response DTO
     * @throws com.contactapp.exception.DuplicateEmailException if email already exists
     */
    ContactResponseDTO createContact(ContactRequestDTO dto);

    /**
     * Returns all contacts in the system.
     *
     * @return list of contact response DTOs (may be empty)
     */
    List<ContactResponseDTO> getAllContacts();

    /**
     * Finds a single contact by its ID.
     *
     * @param id the contact's primary key
     * @return the contact response DTO
     * @throws com.contactapp.exception.ContactNotFoundException if no contact exists with the given ID
     */
    ContactResponseDTO getContactById(Long id);

    /**
     * Searches contacts whose name contains the given string (case-insensitive).
     *
     * @param name the substring to search for
     * @return list of matching contact response DTOs (may be empty)
     */
    List<ContactResponseDTO> searchByName(String name);

    /**
     * Updates an existing contact by ID.
     *
     * @param id  the ID of the contact to update
     * @param dto the updated contact data
     * @return the updated contact response DTO
     * @throws com.contactapp.exception.ContactNotFoundException if no contact exists with the given ID
     * @throws com.contactapp.exception.DuplicateEmailException  if the new email is already taken by another contact
     */
    ContactResponseDTO updateContact(Long id, ContactRequestDTO dto);

    /**
     * Deletes a single contact by ID.
     *
     * @param id the contact's primary key
     * @throws com.contactapp.exception.ContactNotFoundException if no contact exists with the given ID
     */
    void deleteContact(Long id);

    /**
     * Deletes every contact in the system.
     */
    void deleteAllContacts();
}
