package com.contactapp.service;

import com.contactapp.dto.request.ContactRequestDTO;
import com.contactapp.dto.response.ContactResponseDTO;
import com.contactapp.exception.ContactNotFoundException;
import com.contactapp.exception.DuplicateEmailException;
import com.contactapp.model.Contact;
import com.contactapp.repository.ContactRepository;
import com.contactapp.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    private Contact sampleContact;
    private ContactRequestDTO sampleRequest;

    @BeforeEach
    void setUp() {
        sampleContact = new Contact("Alice Smith", "+1-555-0101", "alice@example.com", "123 Main St");
        sampleContact.setId(1L);
        sampleContact.setCreatedAt(LocalDateTime.now());
        sampleContact.setUpdatedAt(LocalDateTime.now());

        sampleRequest = new ContactRequestDTO("Alice Smith", "+1-555-0101", "alice@example.com", "123 Main St");
    }

    @Test
    @DisplayName("createContact — success: saves and returns DTO")
    void createContact_success() {
        when(contactRepository.findByEmail(sampleRequest.getEmail())).thenReturn(null);
        when(contactRepository.save(any(Contact.class))).thenReturn(sampleContact);

        ContactResponseDTO result = contactService.createContact(sampleRequest);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Alice Smith");
        assertThat(result.getEmail()).isEqualTo("alice@example.com");
        verify(contactRepository).save(any(Contact.class));
    }

    @Test
    @DisplayName("createContact — throws DuplicateEmailException when email exists")
    void createContact_duplicateEmail_throwsException() {
        when(contactRepository.findByEmail(sampleRequest.getEmail())).thenReturn(sampleContact);

        assertThatThrownBy(() -> contactService.createContact(sampleRequest))
                .isInstanceOf(DuplicateEmailException.class)
                .hasMessageContaining("alice@example.com");

        verify(contactRepository, never()).save(any());
    }

    @Test
    @DisplayName("getAllContacts — returns mapped list")
    void getAllContacts_returnsList() {
        Contact second = new Contact("Bob Jones", "+1-555-0202", "bob@example.com", "456 Oak Ave");
        second.setId(2L);
        second.setCreatedAt(LocalDateTime.now());
        second.setUpdatedAt(LocalDateTime.now());

        when(contactRepository.findAll()).thenReturn(List.of(sampleContact, second));

        List<ContactResponseDTO> results = contactService.getAllContacts();

        assertThat(results).hasSize(2);
        assertThat(results.get(0).getEmail()).isEqualTo("alice@example.com");
        assertThat(results.get(1).getEmail()).isEqualTo("bob@example.com");
    }

    @Test
    @DisplayName("getAllContacts — returns empty list when no contacts")
    void getAllContacts_empty() {
        when(contactRepository.findAll()).thenReturn(List.of());

        List<ContactResponseDTO> results = contactService.getAllContacts();

        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("getContactById — returns DTO for existing ID")
    void getContactById_found() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(sampleContact));

        ContactResponseDTO result = contactService.getContactById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Alice Smith");
    }

    @Test
    @DisplayName("getContactById — throws ContactNotFoundException for unknown ID")
    void getContactById_notFound() {
        when(contactRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.getContactById(99L))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("searchByName — returns matching contacts")
    void searchByName_found() {
        when(contactRepository.findByNameContainingIgnoreCase("alice"))
                .thenReturn(List.of(sampleContact));

        List<ContactResponseDTO> results = contactService.searchByName("alice");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Alice Smith");
    }

    @Test
    @DisplayName("updateContact — success: updates fields and returns DTO")
    void updateContact_success() {
        ContactRequestDTO updateReq = new ContactRequestDTO("Alice Updated", "+1-555-9999",
                "alice@example.com", "789 Pine Rd");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(sampleContact));
        when(contactRepository.findByEmail(updateReq.getEmail())).thenReturn(sampleContact); // same contact
        when(contactRepository.save(any(Contact.class))).thenAnswer(inv -> inv.getArgument(0));

        ContactResponseDTO result = contactService.updateContact(1L, updateReq);

        assertThat(result.getName()).isEqualTo("Alice Updated");
        assertThat(result.getPhone()).isEqualTo("+1-555-9999");
        verify(contactRepository).save(any(Contact.class));
    }

    @Test
    @DisplayName("updateContact — throws ContactNotFoundException for unknown ID")
    void updateContact_notFound() {
        when(contactRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.updateContact(99L, sampleRequest))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("updateContact — throws DuplicateEmailException when email used by another contact")
    void updateContact_emailTakenByAnother() {
        Contact other = new Contact("Other Person", "+1-555-3333", "alice@example.com", "");
        other.setId(2L);

        when(contactRepository.findById(1L)).thenReturn(Optional.of(sampleContact));
        when(contactRepository.findByEmail("alice@example.com")).thenReturn(other);

        assertThatThrownBy(() -> contactService.updateContact(1L, sampleRequest))
                .isInstanceOf(DuplicateEmailException.class);
    }

    @Test
    @DisplayName("deleteContact — success: deletes existing contact")
    void deleteContact_success() {
        when(contactRepository.existsById(1L)).thenReturn(true);
        doNothing().when(contactRepository).deleteById(1L);

        assertThatCode(() -> contactService.deleteContact(1L)).doesNotThrowAnyException();
        verify(contactRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deleteContact — throws ContactNotFoundException for unknown ID")
    void deleteContact_notFound() {
        when(contactRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> contactService.deleteContact(99L))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("99");

        verify(contactRepository, never()).deleteById(any());
    }

    @Test
    @DisplayName("deleteAllContacts — calls repository deleteAll")
    void deleteAllContacts_success() {
        doNothing().when(contactRepository).deleteAll();

        assertThatCode(() -> contactService.deleteAllContacts()).doesNotThrowAnyException();
        verify(contactRepository).deleteAll();
    }
}
