package com.fundoo.fundoonotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundoo.fundoonotes.dto.NoteLabelRequest;
import com.fundoo.fundoonotes.dto.NoteRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * NoteLabelTest — UC6 Integration Tests
 *
 * Verifies all acceptance criteria for Labels/Tags Management:
 *
 *  1.  Create label → 201, returns label JSON
 *  2.  Duplicate label (same user) → 409 Conflict
 *  3.  Two users can have the same label name (per-user uniqueness)
 *  4.  Get label list → only active (non-deleted) labels returned
 *  5.  Update label → 200, new name returned
 *  6.  Update to conflicting name → 409
 *  7.  Add label to note → note response includes label in labels[]
 *  8.  Remove label from note → note response excludes label
 *  9.  Soft-delete label → excluded from getNoteLabelList
 *  10. Apply a deleted label to a note → 400 Bad Request
 *  11. IDOR: User B cannot manage User A's labels (404)
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoteLabelTest {

    @Autowired MockMvc     mockMvc;
    @Autowired ObjectMapper objectMapper;

    // Shared state across ordered tests
    private static String tokenA;
    private static String tokenB;
    private static long   labelId;
    private static long   noteId;
    private static long   labelBId;

    // ── Setup ─────────────────────────────────────────────────────────────

    @BeforeAll
    static void registerUsers(@Autowired MockMvc mvc,
                               @Autowired ObjectMapper mapper) throws Exception {
        // Register User A
        String regA = mvc.perform(post("/user/userSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"firstName":"Alice","lastName":"Test",
                     "email":"alice.uc6@test.com","password":"Password1!"}
                    """))
                .andReturn().getResponse().getContentAsString();
        tokenA = mapper.readTree(regA).get("token").asText();

        // Register User B
        String regB = mvc.perform(post("/user/userSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"firstName":"Bob","lastName":"Test",
                     "email":"bob.uc6@test.com","password":"Password1!"}
                    """))
                .andReturn().getResponse().getContentAsString();
        tokenB = mapper.readTree(regB).get("token").asText();

        // Create a note for User A to attach labels to
        String noteJson = mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"My UC6 Note\",\"description\":\"Test\"}"))
                .andReturn().getResponse().getContentAsString();
        noteId = mapper.readTree(noteJson).get("noteId").asLong();
    }

    // ── 1. Create Label ───────────────────────────────────────────────────

    @Test @Order(1)
    @DisplayName("Create label → 201 with correct response shape")
    void createLabel_success() throws Exception {
        String body = mockMvc.perform(post("/noteLabels")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Work\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",        notNullValue()))
                .andExpect(jsonPath("$.label",     is("Work")))
                .andExpect(jsonPath("$.isDeleted", is(false)))
                .andExpect(jsonPath("$.userId",    notNullValue()))
                .andReturn().getResponse().getContentAsString();

        labelId = objectMapper.readTree(body).get("id").asLong();
    }

    // ── 2. Duplicate Label Same User → 409 ────────────────────────────────

    @Test @Order(2)
    @DisplayName("Duplicate label for same user → 409 Conflict")
    void createLabel_duplicate_sameUser_returns409() throws Exception {
        mockMvc.perform(post("/noteLabels")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Work\"}"))
                .andExpect(status().isConflict());
    }

    // ── 3. Different Users — Same Label Name Allowed ───────────────────────

    @Test @Order(3)
    @DisplayName("Two different users can each have a 'Work' label")
    void createLabel_differentUsers_sameName_allowed() throws Exception {
        String body = mockMvc.perform(post("/noteLabels")
                .header("Authorization", "Bearer " + tokenB)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Work\"}"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        labelBId = objectMapper.readTree(body).get("id").asLong();
    }

    // ── 4. Get Label List — Active Only ────────────────────────────────────

    @Test @Order(4)
    @DisplayName("getNoteLabelList — returns only active labels")
    void getNoteLabelList_returnsActiveLabels() throws Exception {
        mockMvc.perform(get("/noteLabels/getNoteLabelList")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",       hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].label", is("Work")));
    }

    // ── 5. Update Label ────────────────────────────────────────────────────

    @Test @Order(5)
    @DisplayName("Update label → 200, new name returned")
    void updateLabel_success() throws Exception {
        mockMvc.perform(patch("/noteLabels/" + labelId)
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Personal\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label", is("Personal")));
    }

    // ── 6. Update to Conflicting Name → 409 ───────────────────────────────

    @Test @Order(6)
    @DisplayName("Update to existing label name → 409 Conflict")
    void updateLabel_conflictingName_returns409() throws Exception {
        // Create a second label first
        mockMvc.perform(post("/noteLabels")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Home\"}"))
                .andExpect(status().isCreated());

        // Try to rename "Personal" to "Home" → should 409
        mockMvc.perform(patch("/noteLabels/" + labelId)
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Home\"}"))
                .andExpect(status().isConflict());
    }

    // ── 7. Add Label to Note ───────────────────────────────────────────────

    @Test @Order(7)
    @DisplayName("Add label to note → note response includes label in labels[]")
    void addLabelToNote_success() throws Exception {
        mockMvc.perform(post("/notes/" + noteId + "/addLabelToNotes/" + labelId + "/add")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.labels",             hasSize(1)))
                .andExpect(jsonPath("$.labels[0].label",    is("Personal")))
                .andExpect(jsonPath("$.labels[0].isDeleted", is(false)));
    }

    // ── 8. Remove Label from Note ──────────────────────────────────────────

    @Test @Order(8)
    @DisplayName("Remove label from note → label array empty, label itself intact")
    void removeLabelFromNote_success() throws Exception {
        mockMvc.perform(post("/notes/" + noteId + "/addLabelToNotes/" + labelId + "/remove")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.labels", hasSize(0)));

        // Confirm label itself still exists in label list
        mockMvc.perform(get("/noteLabels/getNoteLabelList")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.label == 'Personal')]", hasSize(1)));
    }

    // ── 9. Soft-Delete Label ───────────────────────────────────────────────

    @Test @Order(9)
    @DisplayName("Soft-delete label → excluded from getNoteLabelList")
    void deleteLabel_softDelete_excludedFromList() throws Exception {
        mockMvc.perform(delete("/noteLabels/" + labelId + "/deleteNoteLabel")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Label deleted successfully")));

        // Confirm deleted label is gone from the list
        mockMvc.perform(get("/noteLabels/getNoteLabelList")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(jsonPath("$[?(@.label == 'Personal')]", hasSize(0)));
    }

    // ── 10. Apply Deleted Label → 400 ─────────────────────────────────────

    @Test @Order(10)
    @DisplayName("Apply a deleted label to a note → 400 Bad Request")
    void addDeletedLabelToNote_returns400() throws Exception {
        mockMvc.perform(post("/notes/" + noteId + "/addLabelToNotes/" + labelId + "/add")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isBadRequest());
    }

    // ── 11. IDOR — User B Cannot Touch User A's Labels ────────────────────

    @Test @Order(11)
    @DisplayName("IDOR: User B cannot update/delete User A's label → 404")
    void idor_userBCannotAccessUserALabel() throws Exception {
        mockMvc.perform(patch("/noteLabels/" + labelId)
                .header("Authorization", "Bearer " + tokenB)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Stolen\"}"))
                .andExpect(status().isNotFound());

        mockMvc.perform(delete("/noteLabels/" + labelId + "/deleteNoteLabel")
                .header("Authorization", "Bearer " + tokenB))
                .andExpect(status().isNotFound());
    }
}
