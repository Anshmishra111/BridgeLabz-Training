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
 * NoteSearchTest — UC7 Integration Tests
 *
 * Verifies all acceptance criteria for Search & Filter with Specification:
 *
 *  1.  Search by title (partial, case-insensitive) → matching notes returned
 *  2.  Search by title (no match) → empty list
 *  3.  Search by state=pinned → only pinned notes
 *  4.  Search by state=archived → only archived notes
 *  5.  Search by state=deleted → only trashed notes
 *  6.  Search by state=active → only non-archived, non-deleted notes
 *  7.  Search by labelName → only notes tagged with that label
 *  8.  Combined: titleText + state
 *  9.  Combined: titleText + labelName
 *  10. Combined: all three filters
 *  11. getNotesListByLabel/{labelName} convenience endpoint
 *  12. IDOR: owner predicate cannot be bypassed — User B's notes never appear
 *  13. No params → returns all of the owner's notes (no filter)
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoteSearchTest {

    @Autowired MockMvc      mockMvc;
    @Autowired ObjectMapper objectMapper;

    private static String tokenA;
    private static String tokenB;
    private static long   pinnedNoteId;
    private static long   archivedNoteId;
    private static long   trashedNoteId;
    private static long   labelledNoteId;
    private static long   labelId;

    // ── Setup — create notes and labels in known states ───────────────────

    @BeforeAll
    static void setup(@Autowired MockMvc mvc,
                      @Autowired ObjectMapper mapper) throws Exception {

        // Register User A
        String regA = mvc.perform(post("/user/userSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"firstName":"Alice","lastName":"Smith",
                     "email":"alice.uc7@test.com","password":"Password1!"}
                    """))
                .andReturn().getResponse().getContentAsString();
        tokenA = mapper.readTree(regA).get("token").asText();

        // Register User B (for IDOR test)
        String regB = mvc.perform(post("/user/userSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"firstName":"Bob","lastName":"Smith",
                     "email":"bob.uc7@test.com","password":"Password1!"}
                    """))
                .andReturn().getResponse().getContentAsString();
        tokenB = mapper.readTree(regB).get("token").asText();

        // Create User B's note with same title (should NEVER appear in A's search)
        mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenB)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Meeting notes\",\"description\":\"B's note\"}"))
                .andExpect(status().isCreated());

        // Create User A's notes
        String n1 = mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Meeting notes\",\"description\":\"active note\"}"))
                .andReturn().getResponse().getContentAsString();
        long activeNoteId = mapper.readTree(n1).get("noteId").asLong();

        String n2 = mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Sprint planning\",\"description\":\"to pin\"}"))
                .andReturn().getResponse().getContentAsString();
        pinnedNoteId = mapper.readTree(n2).get("noteId").asLong();

        String n3 = mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Old meeting\",\"description\":\"to archive\"}"))
                .andReturn().getResponse().getContentAsString();
        archivedNoteId = mapper.readTree(n3).get("noteId").asLong();

        String n4 = mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Deleted meeting\",\"description\":\"to trash\"}"))
                .andReturn().getResponse().getContentAsString();
        trashedNoteId = mapper.readTree(n4).get("noteId").asLong();

        String n5 = mvc.perform(post("/notes/addNotes")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Work budget\",\"description\":\"labelled note\"}"))
                .andReturn().getResponse().getContentAsString();
        labelledNoteId = mapper.readTree(n5).get("noteId").asLong();

        // Set states
        mvc.perform(post("/notes/pinUnpinNotes?noteId=" + pinnedNoteId)
                .header("Authorization", "Bearer " + tokenA));
        mvc.perform(post("/notes/archiveNotes?noteId=" + archivedNoteId)
                .header("Authorization", "Bearer " + tokenA));
        mvc.perform(post("/notes/trashNotes?noteId=" + trashedNoteId)
                .header("Authorization", "Bearer " + tokenA));

        // Create label "Work" and attach to the labelled note
        String lbl = mvc.perform(post("/noteLabels")
                .header("Authorization", "Bearer " + tokenA)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"label\":\"Work\"}"))
                .andReturn().getResponse().getContentAsString();
        labelId = mapper.readTree(lbl).get("id").asLong();

        mvc.perform(post("/notes/" + labelledNoteId + "/addLabelToNotes/" + labelId + "/add")
                .header("Authorization", "Bearer " + tokenA));
    }

    // ── 1. Title search — match ───────────────────────────────────────────

    @Test @Order(1)
    @DisplayName("titleText search — returns all notes with 'meeting' in title")
    void searchByTitle_match() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?titleText=meeting")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                // "Meeting notes", "Old meeting", "Deleted meeting" — all contain 'meeting'
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(3))));
    }

    // ── 2. Title search — no match ────────────────────────────────────────

    @Test @Order(2)
    @DisplayName("titleText search — no match returns empty list")
    void searchByTitle_noMatch() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?titleText=XYZNOTEXIST")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ── 3. State=pinned ───────────────────────────────────────────────────

    @Test @Order(3)
    @DisplayName("state=pinned — returns only pinned notes")
    void searchByState_pinned() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?state=pinned")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isPined", everyItem(is(true))));
    }

    // ── 4. State=archived ─────────────────────────────────────────────────

    @Test @Order(4)
    @DisplayName("state=archived — returns only archived notes")
    void searchByState_archived() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?state=archived")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isArchived", everyItem(is(true))));
    }

    // ── 5. State=deleted ──────────────────────────────────────────────────

    @Test @Order(5)
    @DisplayName("state=deleted — returns only trashed notes")
    void searchByState_deleted() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?state=deleted")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isDeleted", everyItem(is(true))));
    }

    // ── 6. State=active ───────────────────────────────────────────────────

    @Test @Order(6)
    @DisplayName("state=active — excludes archived and deleted notes")
    void searchByState_active() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?state=active")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isArchived", everyItem(is(false))))
                .andExpect(jsonPath("$[*].isDeleted",  everyItem(is(false))));
    }

    // ── 7. Filter by label ────────────────────────────────────────────────

    @Test @Order(7)
    @DisplayName("labelName filter — returns only notes tagged with 'Work'")
    void searchByLabel() throws Exception {
        mockMvc.perform(get("/notes/searchNotes?labelName=Work")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",               hasSize(1)))
                .andExpect(jsonPath("$[0].noteId",     is((int) labelledNoteId)))
                .andExpect(jsonPath("$[0].labels[0].label", is("Work")));
    }

    // ── 8. Combined: title + state ────────────────────────────────────────

    @Test @Order(8)
    @DisplayName("Combined: titleText + state — intersection of both filters")
    void searchCombined_titleAndState() throws Exception {
        // "Old meeting" is archived — should match title='meeting' AND state='archived'
        mockMvc.perform(get("/notes/searchNotes?titleText=meeting&state=archived")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].isArchived", everyItem(is(true))))
                .andExpect(jsonPath("$[*].title", everyItem(containsStringIgnoringCase("meeting"))));
    }

    // ── 9. Combined: title + label ────────────────────────────────────────

    @Test @Order(9)
    @DisplayName("Combined: titleText + labelName — note must match both")
    void searchCombined_titleAndLabel() throws Exception {
        // "Work budget" has label 'Work' and title contains 'budget'
        mockMvc.perform(get("/notes/searchNotes?titleText=budget&labelName=Work")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is("Work budget")));
    }

    // ── 10. Combined: all three ───────────────────────────────────────────

    @Test @Order(10)
    @DisplayName("Combined: all three filters — title + state + labelName")
    void searchCombined_allThree() throws Exception {
        // No note can match title='XYZNOTEXIST' AND labelName='Work' simultaneously
        mockMvc.perform(get("/notes/searchNotes?titleText=XYZNOTEXIST&state=active&labelName=Work")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // ── 11. Convenience endpoint: getNotesListByLabel ─────────────────────

    @Test @Order(11)
    @DisplayName("getNotesListByLabel/{labelName} — same result as ?labelName=Work")
    void getNotesListByLabel_convenience() throws Exception {
        mockMvc.perform(get("/notes/getNotesListByLabel/Work")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",               hasSize(1)))
                .andExpect(jsonPath("$[0].title",      is("Work budget")));
    }

    // ── 12. IDOR — owner predicate cannot be bypassed ─────────────────────

    @Test @Order(12)
    @DisplayName("IDOR: searchNotes never returns another user's notes")
    void search_ownerPredicateCannotBeBypassed() throws Exception {
        // User B has a note titled "Meeting notes"
        // User A searching 'Meeting notes' must NEVER see User B's note
        String body = mockMvc.perform(get("/notes/searchNotes?titleText=Meeting notes")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Every returned note must belong to User A (userId matches)
        // We check that no result has User B's userId
        var results = objectMapper.readTree(body);
        String userAId = objectMapper.readTree(
                mockMvc.perform(get("/user/profile")
                        .header("Authorization", "Bearer " + tokenA))
                        .andReturn().getResponse().getContentAsString())
                .get("userId").asText();

        for (var node : results) {
            org.junit.jupiter.api.Assertions.assertEquals(
                    userAId, node.get("userId").asText(),
                    "Found a note belonging to another user in search results!");
        }
    }

    // ── 13. No params — all of owner's notes returned ─────────────────────

    @Test @Order(13)
    @DisplayName("No filter params — returns ALL of the owner's notes (any state)")
    void searchNoParams_returnsAllOwnerNotes() throws Exception {
        mockMvc.perform(get("/notes/searchNotes")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                // We created 5 notes for User A
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(5))));
    }
}
