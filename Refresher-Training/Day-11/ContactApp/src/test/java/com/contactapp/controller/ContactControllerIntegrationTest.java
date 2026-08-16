package com.contactapp.controller;

import com.contactapp.dto.request.ContactRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Long createdContactId;

    private ContactRequestDTO validRequest() {
        return new ContactRequestDTO("John Doe", "+1-555-1234", "john@example.com", "1 Test Street");
    }

    @Test
    @Order(1)
    @DisplayName("POST /api/contacts — 201 Created with valid body")
    void createContact_success() throws Exception {
        MvcResult result = mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.createdAt").exists())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        createdContactId = objectMapper.readTree(body).get("id").asLong();
    }

    @Test
    @Order(2)
    @DisplayName("POST /api/contacts — 409 Conflict on duplicate email")
    void createContact_duplicateEmail_returns409() throws Exception {
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.error").value("Conflict"));
    }

    @Test
    @Order(3)
    @DisplayName("POST /api/contacts — 400 Bad Request on missing required fields")
    void createContact_missingName_returns400() throws Exception {
        ContactRequestDTO invalid = new ContactRequestDTO("", "+1-555-9999", "invalid", "");

        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").exists()) // validation error field
                .andExpect(jsonPath("$.email").exists());
    }

    @Test
    @Order(4)
    @DisplayName("GET /api/contacts — 200 OK returns list")
    void getAllContacts_returnsList() throws Exception {
        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    @Order(5)
    @DisplayName("GET /api/contacts/{id} — 200 OK for existing contact")
    void getContactById_found() throws Exception {
        mockMvc.perform(get("/api/contacts/{id}", createdContactId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdContactId))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    @Order(6)
    @DisplayName("GET /api/contacts/{id} — 404 for non-existent ID")
    void getContactById_notFound() throws Exception {
        mockMvc.perform(get("/api/contacts/{id}", 99999L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }

    @Test
    @Order(7)
    @DisplayName("GET /api/contacts/search?name=john — returns matching contacts")
    void searchByName_found() throws Exception {
        mockMvc.perform(get("/api/contacts/search").param("name", "john"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].name", containsStringIgnoringCase("john")));
    }

    @Test
    @Order(8)
    @DisplayName("GET /api/contacts/search?name=zzz — returns empty list for no match")
    void searchByName_noResults() throws Exception {
        mockMvc.perform(get("/api/contacts/search").param("name", "zzznomatch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @Order(9)
    @DisplayName("PUT /api/contacts/{id} — 200 OK with updated fields")
    void updateContact_success() throws Exception {
        ContactRequestDTO update = new ContactRequestDTO(
                "John Updated", "+1-555-9876", "john@example.com", "2 Updated Ave");

        mockMvc.perform(put("/api/contacts/{id}", createdContactId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.phone").value("+1-555-9876"))
                .andExpect(jsonPath("$.updatedAt").exists());
    }

    @Test
    @Order(10)
    @DisplayName("PUT /api/contacts/{id} — 404 for non-existent ID")
    void updateContact_notFound() throws Exception {
        mockMvc.perform(put("/api/contacts/{id}", 99999L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest())))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(11)
    @DisplayName("DELETE /api/contacts/{id} — 204 No Content for existing contact")
    void deleteContact_success() throws Exception {
        mockMvc.perform(delete("/api/contacts/{id}", createdContactId))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(12)
    @DisplayName("DELETE /api/contacts/{id} — 404 for already-deleted contact")
    void deleteContact_notFound() throws Exception {
        mockMvc.perform(delete("/api/contacts/{id}", createdContactId))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(13)
    @DisplayName("DELETE /api/contacts — 204 No Content")
    void deleteAllContacts_success() throws Exception {
        mockMvc.perform(delete("/api/contacts"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(14)
    @DisplayName("GET /api/contacts — empty list after deleteAll")
    void getAllContacts_emptyAfterDeleteAll() throws Exception {
        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
