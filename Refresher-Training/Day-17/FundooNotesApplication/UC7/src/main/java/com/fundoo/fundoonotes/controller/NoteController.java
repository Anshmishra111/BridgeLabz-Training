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
 * NoteController — UC7
 *
 * UC7 additions:
 *   GET /notes/searchNotes?titleText=&state=&labelName=
 *       → general flexible search (any filter combination)
 *   GET /notes/getNotesListByLabel/{labelName}
 *       → dedicated convenience endpoint for label-filtered list
 *
 * Both endpoints enforce owner scoping via NoteSpecification.
 * No userId parameter is ever accepted from the client.
 */
@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService   noteService;
    private final SecurityUtils securityUtils;

    // ── UC4 CRUD ──────────────────────────────────────────────────────────

    @PostMapping("/addNotes")
    public ResponseEntity<NoteResponse> addNote(@Valid @RequestBody NoteRequest req) {
        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.addNote(req, owner));
    }

    @GetMapping("/getNotesList")
    public ResponseEntity<List<NoteResponse>> getNotesList() {
        return ResponseEntity.ok(noteService.getNotesList(securityUtils.getCurrentUser()));
    }

    @GetMapping("/getNotesDetail/{noteId}")
    public ResponseEntity<NoteResponse> getNotesDetail(@PathVariable Long noteId) {
        return ResponseEntity.ok(noteService.getNotesDetail(noteId, securityUtils.getCurrentUser()));
    }

    @PostMapping("/updateNotes")
    public ResponseEntity<NoteResponse> updateNote(
            @RequestParam Long noteId, @Valid @RequestBody NoteRequest req) {
        return ResponseEntity.ok(noteService.updateNote(noteId, req, securityUtils.getCurrentUser()));
    }

    @DeleteMapping("/deleteNotes")
    public ResponseEntity<Map<String, String>> deleteNote(@RequestParam Long noteId) {
        noteService.deleteNote(noteId, securityUtils.getCurrentUser());
        return ResponseEntity.ok(Map.of("message", "Note deleted"));
    }

    // ── UC5 State ─────────────────────────────────────────────────────────

    @PostMapping("/pinUnpinNotes")
    public ResponseEntity<NoteResponse> pinUnpinNotes(@RequestParam Long noteId) {
        return ResponseEntity.ok(noteService.pinUnpinNotes(noteId, securityUtils.getCurrentUser()));
    }

    @PostMapping("/archiveNotes")
    public ResponseEntity<NoteResponse> archiveNotes(@RequestParam Long noteId) {
        return ResponseEntity.ok(noteService.archiveNotes(noteId, securityUtils.getCurrentUser()));
    }

    @PostMapping("/trashNotes")
    public ResponseEntity<NoteResponse> trashNotes(@RequestParam Long noteId) {
        return ResponseEntity.ok(noteService.trashNotes(noteId, securityUtils.getCurrentUser()));
    }

    @PostMapping("/deleteForeverNotes")
    public ResponseEntity<Map<String, String>> deleteForeverNotes(@RequestParam Long noteId) {
        noteService.deleteForeverNotes(noteId, securityUtils.getCurrentUser());
        return ResponseEntity.ok(Map.of("message", "Note permanently deleted"));
    }

    @GetMapping("/getArchiveNotesList")
    public ResponseEntity<List<NoteResponse>> getArchiveNotesList() {
        return ResponseEntity.ok(noteService.getArchiveNotesList(securityUtils.getCurrentUser()));
    }

    @GetMapping("/getTrashNotesList")
    public ResponseEntity<List<NoteResponse>> getTrashNotesList() {
        return ResponseEntity.ok(noteService.getTrashNotesList(securityUtils.getCurrentUser()));
    }

    // ── UC6 Label ↔ Note ─────────────────────────────────────────────────

    @PostMapping("/{noteId}/addLabelToNotes/{labelId}/add")
    public ResponseEntity<NoteResponse> addLabelToNote(
            @PathVariable Long noteId, @PathVariable Long labelId) {
        return ResponseEntity.ok(
                noteService.addLabelToNote(noteId, labelId, securityUtils.getCurrentUser()));
    }

    @PostMapping("/{noteId}/addLabelToNotes/{labelId}/remove")
    public ResponseEntity<NoteResponse> removeLabelFromNote(
            @PathVariable Long noteId, @PathVariable Long labelId) {
        return ResponseEntity.ok(
                noteService.removeLabelFromNote(noteId, labelId, securityUtils.getCurrentUser()));
    }

    // ── UC7 Search & Filter ───────────────────────────────────────────────

    /**
     * GET /notes/searchNotes
     *
     * Flexible search endpoint — all query parameters are optional and combinable.
     *
     * @param titleText  partial title match (case-insensitive)
     * @param state      "active" | "pinned" | "archived" | "deleted"
     * @param labelName  exact label name
     *
     * Examples:
     *   ?titleText=meeting
     *   ?state=pinned
     *   ?labelName=Work
     *   ?titleText=report&state=archived
     *   ?titleText=budget&labelName=Work&state=active
     */
    @GetMapping("/searchNotes")
    public ResponseEntity<List<NoteResponse>> searchNotes(
            @RequestParam(required = false) String titleText,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String labelName) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.searchNotes(owner, titleText, state, labelName));
    }

    /**
     * GET /notes/getNotesListByLabel/{labelName}
     *
     * Dedicated convenience endpoint for filtering by label.
     * Equivalent to ?labelName={labelName} on searchNotes,
     * but kept as a named shortcut as present in the API doc.
     */
    @GetMapping("/getNotesListByLabel/{labelName}")
    public ResponseEntity<List<NoteResponse>> getNotesListByLabel(
            @PathVariable String labelName) {

        User owner = securityUtils.getCurrentUser();
        return ResponseEntity.ok(noteService.getNotesListByLabel(owner, labelName));
    }
}
