package com.fundoo.fundoonotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundoo.fundoonotes.dto.NoteRequest;
import com.fundoo.fundoonotes.dto.RegisterRequest;
import com.fundoo.fundoonotes.entity.TypeOfNote;
import com.fundoo.fundoonotes.repository.NoteRepository;
import com.fundoo.fundoonotes.repository.UserRepository;
import org.junit.jupiter.api.*;
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

/**
 * UC4 Integration Tests — Notes CRUD with Ownership
 *
 * Verifies:
 *  1. Create note (addNotes) returns 201 with saved details.
 *  2. Read note (getNotesDetail) works for own note.
 *  3. Update note (updateNotes) works for own note.
 *  4. Delete note (deleteNotes) works for own note.
 *  5. User B CANNOT read, update, or delete User A's note (returns 404 Not Found to prevent info leakage).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotesOwnershipTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    @Autowired NoteRepository noteRepository;

    private static String tokenA;
    private static String tokenB;
    private static Long noteIdA;

    @BeforeEach
    void setupUsers() throws Exception {
        // Idempotent database setup
        if (tokenA == null) {
            noteRepository.deleteAll();
            userRepository.deleteAll();
            tokenA = registerAndGetToken("UserA", "usera_uc4@fundoo.com");
            tokenB = registerAndGetToken("UserB", "userb_uc4@fundoo.com");
        }
    }

    private String registerAndGetToken(String firstName, String email) throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.setFirstName(firstName);
        req.setLastName("TestUser");
        req.setEmail(email);
        req.setPassword("Pass@1234!");

        MvcResult result = mockMvc.perform(post("/user/userSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString())
                .get("token").asText();
    }

    // ── Create Note ────────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Create Note — User A creates a note successfully")
    void createNote_success() throws Exception {
        NoteRequest req = new NoteRequest();
        req.setTitle("User A Note");
        req.setDescription("Description of A");
        req.setColor("#FF5733");
        req.setTypeOfNote(TypeOfNote.TEXT);

        MvcResult result = mockMvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("User A Note")))
                .andExpect(jsonPath("$.description", is("Description of A")))
                .andExpect(jsonPath("$.color", is("#FF5733")))
                .andExpect(jsonPath("$.typeOfNote", is("TEXT")))
                .andExpect(jsonPath("$.noteId", notNullValue()))
                .andReturn();

        noteIdA = objectMapper.readTree(result.getResponse().getContentAsString())
                .get("noteId").asLong();
    }

    // ── Read Note (Success) ────────────────────────────────────────────────

    @Test
    @Order(2)
    @DisplayName("Read Note — User A can read their own note")
    void readOwnNote_success() throws Exception {
        mockMvc.perform(get("/notes/getNotesDetail/" + noteIdA)
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("User A Note")))
                .andExpect(jsonPath("$.description", is("Description of A")));
    }

    // ── Read Note (IDOR Protection) ────────────────────────────────────────

    @Test
    @Order(3)
    @DisplayName("Read Note — User B trying to read User A's note returns 404")
    void readOtherNote_returns404() throws Exception {
        mockMvc.perform(get("/notes/getNotesDetail/" + noteIdA)
                .header("Authorization", "Bearer " + tokenB))
                .andExpect(status().isNotFound()); // Information leak protection
    }

    // ── Update Note (Success) ──────────────────────────────────────────────

    @Test
    @Order(4)
    @DisplayName("Update Note — User A can update their own note")
    void updateOwnNote_success() throws Exception {
        NoteRequest req = new NoteRequest();
        req.setTitle("User A Note Updated");
        req.setDescription("Updated Description");
        req.setColor("#123456");
        req.setTypeOfNote(TypeOfNote.IMAGE);
        req.setImageUrl("http://image.url");

        mockMvc.perform(post("/notes/updateNotes")
                .param("noteId", String.valueOf(noteIdA))
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("User A Note Updated")))
                .andExpect(jsonPath("$.description", is("Updated Description")))
                .andExpect(jsonPath("$.imageUrl", is("http://image.url")))
                .andExpect(jsonPath("$.typeOfNote", is("IMAGE")));
    }

    // ── Update Note (IDOR Protection) ──────────────────────────────────────

    @Test
    @Order(5)
    @DisplayName("Update Note — User B trying to update User A's note returns 404")
    void updateOtherNote_returns404() throws Exception {
        NoteRequest req = new NoteRequest();
        req.setTitle("Tampered");
        req.setDescription("Tampered desc");

        mockMvc.perform(post("/notes/updateNotes")
                .param("noteId", String.valueOf(noteIdA))
                .header("Authorization", "Bearer " + tokenB)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isNotFound()); // Information leak protection
    }

    // ── Delete Note (IDOR Protection) ──────────────────────────────────────

    @Test
    @Order(6)
    @DisplayName("Delete Note — User B trying to delete User A's note returns 404")
    void deleteOtherNote_returns404() throws Exception {
        mockMvc.perform(delete("/notes/deleteNotes/" + noteIdA)
                .header("Authorization", "Bearer " + tokenB))
                .andExpect(status().isNotFound()); // Information leak protection
    }

    // ── Delete Note (Success) ──────────────────────────────────────────────

    @Test
    @Order(7)
    @DisplayName("Delete Note — User A can delete their own note")
    void deleteOwnNote_success() throws Exception {
        mockMvc.perform(delete("/notes/deleteNotes/" + noteIdA)
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Note deleted successfully")));

        // Confirm it is gone
        mockMvc.perform(get("/notes/getNotesDetail/" + noteIdA)
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isNotFound());
    }
}
