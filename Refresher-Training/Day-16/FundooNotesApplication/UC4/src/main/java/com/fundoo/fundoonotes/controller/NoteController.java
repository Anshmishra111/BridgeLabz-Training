package com.fundoo.fundoonotes.controller;

import com.fundoo.fundoonotes.dto.NoteRequest;
import com.fundoo.fundoonotes.dto.NoteResponse;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.security.SecurityUtils;
import com.fundoo.fundoonotes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * NoteController — UC4
 *
 * Endpoints for Notes CRUD with strict ownership checking.
 * Identity is derived purely from SecurityContextHolder via SecurityUtils.
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService   noteService;
    private final SecurityUtils securityUtils;

    // ── Create ────────────────────────────────────────────────────────────

    /**
     * POST /notes/addNotes
     * Scoped to the authenticated user.
     */
    @PostMapping("/addNotes")
    public ResponseEntity<NoteResponse> addNote(@RequestBody NoteRequest req) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.addNote(req, owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ── Read List ─────────────────────────────────────────────────────────

    /**
     * GET /notes/getNotesList
     * Returns all non-deleted notes of the caller.
     */
    @GetMapping("/getNotesList")
    public ResponseEntity<List<NoteResponse>> getNotesList() {
        User owner = securityUtils.getCurrentUser();
        List<NoteResponse> list = noteService.getNotesList(owner);
        return ResponseEntity.ok(list);
    }

    // ── Read Detail ───────────────────────────────────────────────────────

    /**
     * GET /notes/getNotesDetail/{noteId}
     * Returns the note details if owned by caller, else 404.
     */
    @GetMapping("/getNotesDetail/{noteId}")
    public ResponseEntity<NoteResponse> getNotesDetail(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.getNotesDetail(noteId, owner);
        return ResponseEntity.ok(response);
    }

    // ── Update ────────────────────────────────────────────────────────────

    /**
     * POST /notes/updateNotes
     * Body contains the update details. The noteId is passed as a query parameter.
     */
    @PostMapping("/updateNotes")
    public ResponseEntity<NoteResponse> updateNote(
            @RequestParam Long noteId,
            @RequestBody NoteRequest req) {

        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.updateNote(noteId, req, owner);
        return ResponseEntity.ok(response);
    }

    /**
     * Alternate / optional mapping to allow path variable updates as well:
     * POST /notes/updateNotes/{noteId}
     */
    @PostMapping("/updateNotes/{noteId}")
    public ResponseEntity<NoteResponse> updateNotePath(
            @PathVariable Long noteId,
            @RequestBody NoteRequest req) {

        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.updateNote(noteId, req, owner);
        return ResponseEntity.ok(response);
    }

    // ── Delete ────────────────────────────────────────────────────────────

    /**
     * DELETE /notes/deleteNotes/{noteId}
     */
    @DeleteMapping("/deleteNotes/{noteId}")
    public ResponseEntity<Map<String, String>> deleteNote(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        noteService.deleteNote(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note deleted successfully"));
    }

    /**
     * Alternate query-param mapping:
     * DELETE /notes/deleteNotes?noteId=xxx
     */
    @DeleteMapping("/deleteNotes")
    public ResponseEntity<Map<String, String>> deleteNoteQuery(@RequestParam Long noteId) {
        User owner = securityUtils.getCurrentUser();
        noteService.deleteNote(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note deleted successfully"));
    }
}
