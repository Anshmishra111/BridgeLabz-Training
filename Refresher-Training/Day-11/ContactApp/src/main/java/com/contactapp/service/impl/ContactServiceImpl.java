package com.contactapp.service.impl;

import com.contactapp.dto.request.ContactRequestDTO;
import com.contactapp.dto.response.ContactResponseDTO;
import com.contactapp.exception.ContactNotFoundException;
import com.contactapp.exception.DuplicateEmailException;
import com.contactapp.mapper.ContactMapper;
import com.contactapp.model.Contact;
import com.contactapp.repository.ContactRepository;
import com.contactapp.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Primary implementation of {@link IContactService}.
 * Handles all business logic for contact CRUD operations.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactRepository contactRepository;

    // -------------------------------------------------------
    // CREATE
    // -------------------------------------------------------
    @Override
    public ContactResponseDTO createContact(ContactRequestDTO dto) {
        // Guard against duplicate emails
        if (contactRepository.findByEmail(dto.getEmail()) != null) {
            throw new DuplicateEmailException(dto.getEmail());
        }
        Contact saved = contactRepository.save(ContactMapper.toEntity(dto));
        return ContactMapper.toResponseDTO(saved);
    }

    // -------------------------------------------------------
    // READ ALL
    // -------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public List<ContactResponseDTO> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(ContactMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------
    // READ BY ID
    // -------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public ContactResponseDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        return ContactMapper.toResponseDTO(contact);
    }

    // -------------------------------------------------------
    // SEARCH BY NAME
    // -------------------------------------------------------
    @Override
    @Transactional(readOnly = true)
    public List<ContactResponseDTO> searchByName(String name) {
        return contactRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ContactMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------
    // UPDATE
    // -------------------------------------------------------
    @Override
    public ContactResponseDTO updateContact(Long id, ContactRequestDTO dto) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));

        // Check if the new email belongs to a different contact
        Contact emailOwner = contactRepository.findByEmail(dto.getEmail());
        if (emailOwner != null && !emailOwner.getId().equals(id)) {
            throw new DuplicateEmailException(dto.getEmail());
        }

        existing.setName(dto.getName());
        existing.setPhone(dto.getPhone());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());

        Contact updated = contactRepository.save(existing);
        return ContactMapper.toResponseDTO(updated);
    }

    // -------------------------------------------------------
    // DELETE BY ID
    // -------------------------------------------------------
    @Override
    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        contactRepository.deleteById(id);
    }

    // -------------------------------------------------------
    // DELETE ALL
    // -------------------------------------------------------
    @Override
    public void deleteAllContacts() {
        contactRepository.deleteAll();
    }
}
