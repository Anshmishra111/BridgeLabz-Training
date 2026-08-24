package com.fundoo.fundoonotes.controller;

import com.fundoo.fundoonotes.dto.NoteRequest;
import com.fundoo.fundoonotes.dto.NoteResponse;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.security.SecurityUtils;
import com.fundoo.fundoonotes.service.NoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * NoteController — UC6
 *
 * UC5 endpoints: Notes CRUD + Pin / Archive / Trash
 * UC6 additions: Label ↔ Note association endpoints
 *
 * New endpoints:
 *   POST /notes/{noteId}/addLabelToNotes/{labelId}/add    → attach label to note
 *   POST /notes/{noteId}/addLabelToNotes/{labelId}/remove → detach label from note
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService    noteService;
    private final SecurityUtils  securityUtils;

    // ── UC4 Create ────────────────────────────────────────────────────────

    @PostMapping("/addNotes")
    public ResponseEntity<NoteResponse> addNote(
            @Valid @RequestBody NoteRequest req) {

        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.addNote(req, owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ── UC4 Read ──────────────────────────────────────────────────────────

    @GetMapping("/getNotesList")
    public ResponseEntity<List<NoteResponse>> getNotesList() {
        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.getNotesList(owner));
    }

    @GetMapping("/getNotesDetail/{noteId}")
    public ResponseEntity<NoteResponse> getNotesDetail(
            @PathVariable Long noteId) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.getNotesDetail(noteId, owner));
    }

    // ── UC4 Update ────────────────────────────────────────────────────────

    @PostMapping("/updateNotes")
    public ResponseEntity<NoteResponse> updateNote(
            @RequestParam Long noteId,
            @Valid @RequestBody NoteRequest req) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.updateNote(noteId, req, owner));
    }

    // ── UC4 Delete ────────────────────────────────────────────────────────

    @DeleteMapping("/deleteNotes")
    public ResponseEntity<Map<String, String>> deleteNote(
            @RequestParam Long noteId) {

        User owner = securityUtils.getCurrentUser();
        noteService.deleteNote(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note deleted"));
    }

    // ── UC5 State ─────────────────────────────────────────────────────────

    @PostMapping("/pinUnpinNotes")
    public ResponseEntity<NoteResponse> pinUnpinNotes(
            @RequestParam Long noteId) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.pinUnpinNotes(noteId, owner));
    }

    @PostMapping("/archiveNotes")
    public ResponseEntity<NoteResponse> archiveNotes(
            @RequestParam Long noteId) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.archiveNotes(noteId, owner));
    }

    @PostMapping("/trashNotes")
    public ResponseEntity<NoteResponse> trashNotes(
            @RequestParam Long noteId) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.trashNotes(noteId, owner));
    }

    @PostMapping("/deleteForeverNotes")
    public ResponseEntity<Map<String, String>> deleteForeverNotes(
            @RequestParam Long noteId) {

        User owner = securityUtils.getCurrentUser();
        noteService.deleteForeverNotes(noteId, owner);
        return ResponseEntity.ok(Map.of("message", "Note permanently deleted"));
    }

    @GetMapping("/getArchiveNotesList")
    public ResponseEntity<List<NoteResponse>> getArchiveNotesList() {
        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.getArchiveNotesList(owner));
    }

    @GetMapping("/getTrashNotesList")
    public ResponseEntity<List<NoteResponse>> getTrashNotesList() {
        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.getTrashNotesList(owner));
    }

    // ── UC6 Label ↔ Note Association ──────────────────────────────────────

    /**
     * POST /notes/{noteId}/addLabelToNotes/{labelId}/add
     *
     * Attaches an existing label to a note.
     * Both note and label must belong to the authenticated user.
     * Returns 400 if the label has been soft-deleted.
     * Returns 404 if note or label is not found / belongs to another user.
     */
    @PostMapping("/{noteId}/addLabelToNotes/{labelId}/add")
    public ResponseEntity<NoteResponse> addLabelToNote(
            @PathVariable Long noteId,
            @PathVariable Long labelId) {

        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.addLabelToNote(noteId, labelId, owner);
        return ResponseEntity.ok(response);
    }

    /**
     * POST /notes/{noteId}/addLabelToNotes/{labelId}/remove
     *
     * Detaches a label from a note.
     * The label itself is NOT deleted — it remains usable on other notes.
     * Returns 404 if note or label is not found / belongs to another user.
     */
    @PostMapping("/{noteId}/addLabelToNotes/{labelId}/remove")
    public ResponseEntity<NoteResponse> removeLabelFromNote(
            @PathVariable Long noteId,
            @PathVariable Long labelId) {

        User owner = securityUtils.getCurrentUser();
        NoteResponse response = noteService.removeLabelFromNote(noteId, labelId, owner);
        return ResponseEntity.ok(response);
    }
}
