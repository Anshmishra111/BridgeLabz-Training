package com.fundoo.fundoonotes.service;

import com.fundoo.fundoonotes.dto.NoteRequest;
import com.fundoo.fundoonotes.dto.NoteResponse;
import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.entity.TypeOfNote;
import com.fundoo.fundoonotes.exception.InvalidNoteStateException;
import com.fundoo.fundoonotes.exception.ResourceNotFoundException;
import com.fundoo.fundoonotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * NoteService — UC5
 * Business logic for Notes CRUD and State transitions (Pin, Archive, Trash).
 * Scopes all operations to the authenticated user (owner).
 */
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    // ── Create ────────────────────────────────────────────────────────────

    @Transactional
    public NoteResponse addNote(NoteRequest req, User owner) {
        Note note = Note.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                // State flags default: isPined=false, isArchived=false, isDeleted=false
                // They are NOT set from the request body — use the dedicated UC5 endpoints.
                .color(req.getColor())
                .typeOfNote(req.getTypeOfNote() != null ? req.getTypeOfNote() : TypeOfNote.TEXT)
                .imageUrl(req.getImageUrl())
                .linkUrl(req.getLinkUrl())
                .owner(owner)
                .build();

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    // ── Read Active Notes ──────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesList(User owner) {
        return noteRepository.findByOwnerAndIsArchivedFalseAndIsDeletedFalse(owner)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── Read Detail ───────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public NoteResponse getNotesDetail(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        return mapToResponse(note);
    }

    // ── Update ────────────────────────────────────────────────────────────

    @Transactional
    public NoteResponse updateNote(Long noteId, NoteRequest req, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        if (note.isDeleted()) {
            throw new InvalidNoteStateException(
                    "Cannot update a trashed note. Restore it first via trashNotes toggle.");
        }

        // Only update content fields — state transitions use dedicated endpoints
        note.setTitle(req.getTitle());
        note.setDescription(req.getDescription());
        note.setColor(req.getColor());
        if (req.getTypeOfNote() != null) {
            note.setTypeOfNote(req.getTypeOfNote());
        }
        note.setImageUrl(req.getImageUrl());
        note.setLinkUrl(req.getLinkUrl());

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    // ── UC5 State Modifiers ───────────────────────────────────────────────

    /**
     * Toggles the Pin state.
     * Enforces that soft-deleted / trashed notes cannot be pinned.
     */
    @Transactional
    public NoteResponse pinUnpinNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        if (note.isDeleted()) {
            throw new InvalidNoteStateException("Cannot pin a trashed note");
        }

        note.setPined(!note.isPined());
        if (note.isPined()) {
            note.setArchived(false); // Pinning a note un-archives it automatically
        }

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    /**
     * Toggles the Archive state.
     * Enforces that soft-deleted / trashed notes cannot be archived.
     */
    @Transactional
    public NoteResponse archiveNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        if (note.isDeleted()) {
            throw new InvalidNoteStateException("Cannot archive a trashed note");
        }

        note.setArchived(!note.isArchived());
        if (note.isArchived()) {
            note.setPined(false); // Archiving a note un-pins it automatically
        }

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    /**
     * Toggles the Trash state (soft delete).
     * Soft-deleting a note automatically un-pins and un-archives it.
     */
    @Transactional
    public NoteResponse trashNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        note.setDeleted(!note.isDeleted());
        if (note.isDeleted()) {
            note.setPined(false);
            note.setArchived(false);
        }

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    /**
     * Hard deletes a note from the database forever.
     *
     * UC5 Rule: A note MUST be in the trash (isDeleted=true) before it can be
     * permanently deleted. This enforces the two-tier deletion model:
     *   Tier 1 — trashNotes   : soft-delete (isDeleted = true)
     *   Tier 2 — deleteForever: hard-delete (removes from DB)
     *
     * @throws InvalidNoteStateException if the note has not been trashed first
     */
    @Transactional
    public void deleteForeverNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        if (!note.isDeleted()) {
            throw new InvalidNoteStateException(
                    "Note must be in Trash before it can be permanently deleted. " +
                    "Use trashNotes first.");
        }

        noteRepository.delete(note);
    }

    /**
     * Fallback hard-delete (backwards-compat for the UC4 DELETE /notes/deleteNotes endpoint).
     * Does NOT require the note to be in trash — the old DELETE endpoint was a direct hard-delete.
     */
    @Transactional
    public void deleteNote(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));
        noteRepository.delete(note);
    }

    // ── UC5 Read State Lists ──────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<NoteResponse> getArchiveNotesList(User owner) {
        return noteRepository.findByOwnerAndIsArchivedTrueAndIsDeletedFalse(owner)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NoteResponse> getTrashNotesList(User owner) {
        return noteRepository.findByOwnerAndIsDeletedTrue(owner)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── Helper ────────────────────────────────────────────────────────────

    private NoteResponse mapToResponse(Note note) {
        return NoteResponse.builder()
                .noteId(note.getNoteId())
                .title(note.getTitle())
                .description(note.getDescription())
                .isPined(note.isPined())
                .isArchived(note.isArchived())
                .isDeleted(note.isDeleted())
                .color(note.getColor())
                .typeOfNote(note.getTypeOfNote())
                .imageUrl(note.getImageUrl())
                .linkUrl(note.getLinkUrl())
                .userId(note.getOwner().getId())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }
}
