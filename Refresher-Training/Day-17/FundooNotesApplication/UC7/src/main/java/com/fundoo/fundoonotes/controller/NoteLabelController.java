package com.fundoo.fundoonotes.controller;

import com.fundoo.fundoonotes.dto.NoteLabelRequest;
import com.fundoo.fundoonotes.dto.NoteLabelResponse;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.security.SecurityUtils;
import com.fundoo.fundoonotes.service.NoteLabelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * NoteLabelController — UC6
 *
 * Manages label/tag lifecycle: create, update, soft-delete, list.
 * All label-to-note associations are managed via NoteController.
 *
 * Identity is derived from SecurityContextHolder via SecurityUtils.
 * UserId is NEVER accepted as a request parameter (IDOR prevention).
 *
 * Endpoints (matching API doc exactly):
 *   POST   /noteLabels                           → create label
 *   PATCH  /noteLabels/{id}                      → update label text
 *   DELETE /noteLabels/{id}/deleteNoteLabel      → soft-delete label
 *   GET    /noteLabels/getNoteLabelList           → list active labels
 */
@RestController
@RequestMapping("/noteLabels")
@RequiredArgsConstructor
public class NoteLabelController {

    private final NoteLabelService noteLabelService;
    private final SecurityUtils    securityUtils;

    // ── Create ────────────────────────────────────────────────────────────

    /**
     * POST /noteLabels
     * Creates a new label for the authenticated user.
     * Returns 409 if the user already has an active label with the same text.
     */
    @PostMapping
    public ResponseEntity<NoteLabelResponse> createLabel(
            @Valid @RequestBody NoteLabelRequest req) {

        User owner = securityUtils.getCurrentUser();
        NoteLabelResponse response = noteLabelService.createLabel(req, owner);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ── Update ────────────────────────────────────────────────────────────

    /**
     * PATCH /noteLabels/{id}
     * Updates the text of an existing label.
     * Returns 409 if the new name conflicts with another active label.
     * Returns 400 if the label is already soft-deleted.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<NoteLabelResponse> updateLabel(
            @PathVariable Long id,
            @Valid @RequestBody NoteLabelRequest req) {

        User owner = securityUtils.getCurrentUser();
        NoteLabelResponse response = noteLabelService.updateLabel(id, req, owner);
        return ResponseEntity.ok(response);
    }

    // ── Soft Delete ───────────────────────────────────────────────────────

    /**
     * DELETE /noteLabels/{id}/deleteNoteLabel
     * Soft-deletes a label (isDeleted = true).
     * The label stays in the DB and remains on notes it was applied to
     * — it is simply hidden from getNoteLabelList.
     */
    @DeleteMapping("/{id}/deleteNoteLabel")
    public ResponseEntity<Map<String, String>> deleteLabel(
            @PathVariable Long id) {

        User owner = securityUtils.getCurrentUser();
        noteLabelService.deleteLabel(id, owner);
        return ResponseEntity.ok(Map.of("message", "Label deleted successfully"));
    }

    // ── List Active Labels ────────────────────────────────────────────────

    /**
     * GET /noteLabels/getNoteLabelList
     * Returns all active (non-deleted) labels for the authenticated user.
     */
    @GetMapping("/getNoteLabelList")
    public ResponseEntity<List<NoteLabelResponse>> getNoteLabelList() {

        User owner = securityUtils.getCurrentUser();
        List<NoteLabelResponse> labels = noteLabelService.getNoteLabelList(owner);
        return ResponseEntity.ok(labels);
    }
}
