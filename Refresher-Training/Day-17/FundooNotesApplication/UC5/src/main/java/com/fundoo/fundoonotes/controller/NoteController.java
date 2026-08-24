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
 * NoteController — UC5
 *
 * Endpoints for Notes CRUD and Note State Transitions (Pin, Archive, Trash, deleteForever).
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
     * Returns all active notes (non-archived, non-deleted) of the caller.
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
     * Alternate mapping to allow path variable updates as well:
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

    // ── UC5 Pin / Unpin ───────────────────────────────────────────────────

    /**
     * POST /notes/pinUnpinNotes
     * Toggles pin state. Can pass noteId via query parameter.
     */
    @PostMapping("/pinUnpinNotes")
    public ResponseEntity<NoteResponse> pinUnpinNotes(@RequestParam Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.pinUnpinNotes(noteId, owner);
        return ResponseEntity.ok(response);
    }

    /**
     * Alternate path param mapping: POST /notes/pinUnpinNotes/{noteId}
     */
    @PostMapping("/pinUnpinNotes/{noteId}")
    public ResponseEntity<NoteResponse> pinUnpinNotesPath(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.pinUnpinNotes(noteId, owner);
        return ResponseEntity.ok(response);
    }

    // ── UC5 Archive ───────────────────────────────────────────────────────

    /**
     * POST /notes/archiveNotes
     * Toggles archive state. Can pass noteId via query parameter.
     */
    @PostMapping("/archiveNotes")
    public ResponseEntity<NoteResponse> archiveNotes(@RequestParam Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.archiveNotes(noteId, owner);
        return ResponseEntity.ok(response);
    }

    /**
     * Alternate path param mapping: POST /notes/archiveNotes/{noteId}
     */
    @PostMapping("/archiveNotes/{noteId}")
    public ResponseEntity<NoteResponse> archiveNotesPath(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.archiveNotes(noteId, owner);
        return ResponseEntity.ok(response);
    }

    // ── UC5 Trash ─────────────────────────────────────────────────────────

    /**
     * POST /notes/trashNotes
     * Toggles soft delete. Can pass noteId via query parameter.
     */
    @PostMapping("/trashNotes")
    public ResponseEntity<NoteResponse> trashNotes(@RequestParam Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.trashNotes(noteId, owner);
        return ResponseEntity.ok(response);
    }

    /**
     * Alternate path param mapping: POST /notes/trashNotes/{noteId}
     */
    @PostMapping("/trashNotes/{noteId}")
    public ResponseEntity<NoteResponse> trashNotesPath(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.trashNotes(noteId, owner);
        return ResponseEntity.ok(response);
    }

    // ── UC5 Delete Forever ────────────────────────────────────────────────

    /**
     * POST /notes/deleteForeverNotes
     * Hard deletes a note forever. Can pass noteId via query parameter.
     */
    @PostMapping("/deleteForeverNotes")
    public ResponseEntity<Map<String, String>> deleteForeverNotes(@RequestParam Long noteId) {
        User owner = securityUtils.getCurrentUser();
        noteService.deleteForeverNotes(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note permanently deleted"));
    }

    /**
     * Alternate path param mapping: POST /notes/deleteForeverNotes/{noteId}
     */
    @PostMapping("/deleteForeverNotes/{noteId}")
    public ResponseEntity<Map<String, String>> deleteForeverNotesPath(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        noteService.deleteForeverNotes(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note permanently deleted"));
    }

    // ── UC5 Read State Lists ──────────────────────────────────────────────

    /**
     * GET /notes/getArchiveNotesList
     * Returns archived notes of the caller.
     */
    @GetMapping("/getArchiveNotesList")
    public ResponseEntity<List<NoteResponse>> getArchiveNotesList() {
        User owner = securityUtils.getCurrentUser();
        List<NoteResponse> list = noteService.getArchiveNotesList(owner);
        return ResponseEntity.ok(list);
    }

    /**
     * GET /notes/getTrashNotesList
     * Returns soft-deleted notes of the caller.
     */
    @GetMapping("/getTrashNotesList")
    public ResponseEntity<List<NoteResponse>> getTrashNotesList() {
        User owner = securityUtils.getCurrentUser();
        List<NoteResponse> list = noteService.getTrashNotesList(owner);
        return ResponseEntity.ok(list);
    }

    // ── UC4 Fallback Delete (for backwards compatibility) ──────────────────

    @DeleteMapping("/deleteNotes/{noteId}")
    public ResponseEntity<Map<String, String>> deleteNote(@PathVariable Long noteId) {
        User owner = securityUtils.getCurrentUser();
        noteService.deleteNote(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note deleted successfully"));
    }

    @DeleteMapping("/deleteNotes")
    public ResponseEntity<Map<String, String>> deleteNoteQuery(@RequestParam Long noteId) {
        User owner = securityUtils.getCurrentUser();
        noteService.deleteNote(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note deleted successfully"));
    }
}
