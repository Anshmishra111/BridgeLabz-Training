package com.contactapp.mapper;

import com.contactapp.dto.request.ContactRequestDTO;
import com.contactapp.dto.response.ContactResponseDTO;
import com.contactapp.model.Contact;

public class ContactMapper {

    private ContactMapper() {
    }

    public static Contact toEntity(ContactRequestDTO dto) {
        if (dto == null)
            return null;
        Contact contact = new Contact();
        contact.setName(dto.getName());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setAddress(dto.getAddress());
        return contact;
    }

    public static ContactResponseDTO toResponseDTO(Contact contact) {
        if (contact == null)
            return null;
        ContactResponseDTO dto = new ContactResponseDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setPhone(contact.getPhone());
        dto.setEmail(contact.getEmail());
        dto.setAddress(contact.getAddress());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        return dto;
    }
}
