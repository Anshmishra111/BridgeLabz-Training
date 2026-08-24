package com.fundoo.fundoonotes.service;

import com.fundoo.fundoonotes.dto.NoteLabelRequest;
import com.fundoo.fundoonotes.dto.NoteLabelResponse;
import com.fundoo.fundoonotes.entity.NoteLabel;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.exception.DuplicateLabelException;
import com.fundoo.fundoonotes.exception.InvalidNoteStateException;
import com.fundoo.fundoonotes.exception.ResourceNotFoundException;
import com.fundoo.fundoonotes.repository.NoteLabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * NoteLabelService — UC6
 *
 * Business logic for Label/Tag CRUD.
 * All operations are scoped to the authenticated user.
 *
 * Key rule: Label uniqueness is per-user, not global.
 *   - User A and User B can both have a "Work" label.
 *   - User A CANNOT have two active "Work" labels.
 *   This is enforced via a Service-layer check (not @Column(unique),
 *   which would be global).
 */
@Service
@RequiredArgsConstructor
public class NoteLabelService {

    private final NoteLabelRepository noteLabelRepository;

    // ── Create ────────────────────────────────────────────────────────────

    /**
     * Creates a new label for the authenticated user.
     *
     * @throws DuplicateLabelException if the user already has an active label
     *                                 with the same text.
     */
    @Transactional
    public NoteLabelResponse createLabel(NoteLabelRequest req, User owner) {
        // Per-user uniqueness check (Service layer — cannot use @UniqueConstraint here)
        if (noteLabelRepository.existsByLabelAndOwnerAndIsDeletedFalse(req.getLabel(), owner)) {
            throw new DuplicateLabelException(
                    "Label '" + req.getLabel() + "' already exists for this user");
        }

        NoteLabel label = NoteLabel.builder()
                .label(req.getLabel())
                .owner(owner)
                .build();

        label = noteLabelRepository.save(label);
        return mapToResponse(label);
    }

    // ── Update ────────────────────────────────────────────────────────────

    /**
     * Updates the text of an existing label.
     *
     * @throws ResourceNotFoundException  if the label doesn't belong to this user
     * @throws InvalidNoteStateException  if the label has been soft-deleted
     * @throws DuplicateLabelException    if the new name conflicts with another active label
     */
    @Transactional
    public NoteLabelResponse updateLabel(Long id, NoteLabelRequest req, User owner) {
        NoteLabel label = noteLabelRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Label not found with id: " + id));

        if (label.isDeleted()) {
            throw new InvalidNoteStateException(
                    "Cannot update a deleted label. Restore it first or create a new one.");
        }

        // Only validate uniqueness if the name is actually changing
        if (!label.getLabel().equals(req.getLabel()) &&
                noteLabelRepository.existsByLabelAndOwnerAndIsDeletedFalse(req.getLabel(), owner)) {
            throw new DuplicateLabelException(
                    "Label '" + req.getLabel() + "' already exists for this user");
        }

        label.setLabel(req.getLabel());
        label = noteLabelRepository.save(label);
        return mapToResponse(label);
    }

    // ── Soft Delete ───────────────────────────────────────────────────────

    /**
     * Soft-deletes a label (sets isDeleted=true).
     *
     * The label remains in the DB and stays attached to any notes it was
     * already applied to. It is simply excluded from getNoteLabelList and
     * cannot be applied to new notes.
     *
     * @throws ResourceNotFoundException if the label doesn't belong to this user
     */
    @Transactional
    public void deleteLabel(Long id, User owner) {
        NoteLabel label = noteLabelRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Label not found with id: " + id));

        label.setDeleted(true);
        noteLabelRepository.save(label);
    }

    // ── Read ──────────────────────────────────────────────────────────────

    /**
     * Returns all active (non-deleted) labels for the authenticated user.
     * Deleted labels are never included — matches UC5's soft-delete lesson.
     */
    @Transactional(readOnly = true)
    public List<NoteLabelResponse> getNoteLabelList(User owner) {
        return noteLabelRepository.findByOwnerAndIsDeletedFalse(owner)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── Helper ────────────────────────────────────────────────────────────

    public NoteLabelResponse mapToResponse(NoteLabel label) {
        return NoteLabelResponse.builder()
                .id(label.getId())
                .label(label.getLabel())
                .isDeleted(label.isDeleted())
                .userId(label.getOwner().getId())
                .build();
    }
}
