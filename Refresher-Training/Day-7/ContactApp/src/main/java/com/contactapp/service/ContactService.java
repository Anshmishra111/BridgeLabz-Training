package com.contactapp.service;

import com.contactapp.model.Contact;
import com.contactapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // CREATE
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // READ ALL
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // READ BY ID
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    // SEARCH BY NAME
    public List<Contact> searchByName(String name) {
        return contactRepository.findByNameContainingIgnoreCase(name);
    }

    // UPDATE
    public Contact updateContact(Long id, Contact updatedContact) {
        Contact existing = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));

        existing.setName(updatedContact.getName());
        existing.setPhone(updatedContact.getPhone());
        existing.setEmail(updatedContact.getEmail());
        existing.setAddress(updatedContact.getAddress());

        return contactRepository.save(existing);
    }

    // DELETE
    public String deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
        return "Contact with id " + id + " deleted successfully.";
    }

    // DELETE ALL
    public String deleteAllContacts() {
        contactRepository.deleteAll();
        return "All contacts deleted.";
    }
}
