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
 * UC5 Integration Tests — Pin / Archive / Trash States
 *
 * Verifies:
 *  1. Note is created with default states (not pinned/archived/deleted).
 *  2. pinUnpinNotes toggles pinned state.
 *  3. archiveNotes toggles archived state & unpins.
 *  4. trashNotes soft-deletes & unpins & unarchives.
 *  5. Pinning a trashed note is rejected with 400 Bad Request.
 *  6. getArchiveNotesList/getTrashNotesList correctly filter note states.
 *  7. deleteForeverNotes hard-deletes note permanently.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NotesStateTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired UserRepository userRepository;
    @Autowired NoteRepository noteRepository;

    private static String token;
    private static Long noteId;

    @BeforeEach
    void setupUser() throws Exception {
        if (token == null) {
            noteRepository.deleteAll();
            userRepository.deleteAll();

            RegisterRequest req = new RegisterRequest();
            req.setFirstName("State");
            req.setLastName("TestUser");
            req.setEmail("state_test@fundoo.com");
            req.setPassword("Pass@1234!");

            MvcResult result = mockMvc.perform(post("/user/userSignUp")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                    .andExpect(status().isCreated())
                    .andReturn();

            token = objectMapper.readTree(result.getResponse().getContentAsString())
                    .get("token").asText();
        }
    }

    // ── 1. Create Note ─────────────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("Create Note — Starts in active state (not pinned, archived, or deleted)")
    void createNote_defaultStates() throws Exception {
        NoteRequest req = new NoteRequest();
        req.setTitle("State Test Note");
        req.setDescription("Notes State Machine");
        req.setTypeOfNote(TypeOfNote.TEXT);

        MvcResult result = mockMvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isPined", is(false)))
                .andExpect(jsonPath("$.isArchived", is(false)))
                .andExpect(jsonPath("$.isDeleted", is(false)))
                .andReturn();

        noteId = objectMapper.readTree(result.getResponse().getContentAsString())
                .get("noteId").asLong();
    }

    // ── 2. Pin Unpin ───────────────────────────────────────────────────────

    @Test
    @Order(2)
    @DisplayName("Pin Note — Successfully toggles isPined to true")
    void pinNote_success() throws Exception {
        mockMvc.perform(post("/notes/pinUnpinNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isPined", is(true)));
    }

    // ── 3. Archive Note (Unpins) ───────────────────────────────────────────

    @Test
    @Order(3)
    @DisplayName("Archive Note — Successfully archives and automatically unpins the note")
    void archiveNote_unpinsAutomatically() throws Exception {
        mockMvc.perform(post("/notes/archiveNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isArchived", is(true)))
                .andExpect(jsonPath("$.isPined", is(false)));

        // Verify it shows up in getArchiveNotesList
        mockMvc.perform(get("/notes/getArchiveNotesList")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].noteId", is(noteId.intValue())));

        // Verify it is excluded from active getNotesList
        mockMvc.perform(get("/notes/getNotesList")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ── 4. Trash Note (Soft-deletes & Unarchives) ──────────────────────────

    @Test
    @Order(4)
    @DisplayName("Trash Note — Soft-deletes note, un-archives & un-pins automatically")
    void trashNote_softDeletesNote() throws Exception {
        mockMvc.perform(post("/notes/trashNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isDeleted", is(true)))
                .andExpect(jsonPath("$.isArchived", is(false)))
                .andExpect(jsonPath("$.isPined", is(false)));

        // Verify it shows up in getTrashNotesList
        mockMvc.perform(get("/notes/getTrashNotesList")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].noteId", is(noteId.intValue())));

        // Verify getArchiveNotesList is now empty
        mockMvc.perform(get("/notes/getArchiveNotesList")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ── 5. Reject Pin on Trashed Note ──────────────────────────────────────

    @Test
    @Order(5)
    @DisplayName("Pin Trashed Note — Attempting to pin a soft-deleted note throws 400 Bad Request")
    void pinTrashedNote_rejectedWith400() throws Exception {
        mockMvc.perform(post("/notes/pinUnpinNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Cannot pin a trashed note")));
    }

    // ── 6. Restore from Trash (Toggling Trash back) ────────────────────────

    @Test
    @Order(6)
    @DisplayName("Restore Note — Toggling trash back restores note to active state")
    void restoreNote_success() throws Exception {
        mockMvc.perform(post("/notes/trashNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isDeleted", is(false)));

        // Verify getNotesList has 1 active note
        mockMvc.perform(get("/notes/getNotesList")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    // ── 7. Hard Delete Forever ────────────────────────────────────────────────

    @Test
    @Order(7)
    @DisplayName("Delete Forever — Must trash note first, then permanently hard-delete")
    void deleteForever_removesPermanently() throws Exception {
        // Step 1: Move note to trash first (two-tier deletion model)
        mockMvc.perform(post("/notes/trashNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isDeleted", is(true)));

        // Step 2: Now hard-delete it forever
        mockMvc.perform(post("/notes/deleteForeverNotes")
                .param("noteId", String.valueOf(noteId))
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Note permanently deleted")));

        // Step 3: Confirm it doesn't exist at all
        mockMvc.perform(get("/notes/getNotesDetail/" + noteId)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }
}
