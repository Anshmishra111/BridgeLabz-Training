package com.contactapp.repository;

import com.contactapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Find contacts by name (case-insensitive)
    List<Contact> findByNameContainingIgnoreCase(String name);

    // Find contact by email
    Contact findByEmail(String email);
}
