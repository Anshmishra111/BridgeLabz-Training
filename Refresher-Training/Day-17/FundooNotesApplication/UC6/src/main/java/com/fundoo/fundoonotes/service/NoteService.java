package com.fundoo.fundoonotes.service;

import com.fundoo.fundoonotes.dto.NoteLabelResponse;
import com.fundoo.fundoonotes.dto.NoteRequest;
import com.fundoo.fundoonotes.dto.NoteResponse;
import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.NoteLabel;
import com.fundoo.fundoonotes.entity.TypeOfNote;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.exception.InvalidNoteStateException;
import com.fundoo.fundoonotes.exception.ResourceNotFoundException;
import com.fundoo.fundoonotes.repository.NoteLabelRepository;
import com.fundoo.fundoonotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * NoteService — UC6
 *
 * UC6 additions over UC5:
 *  - addLabelToNote()       : attaches an existing label to a note
 *  - removeLabelFromNote()  : detaches a label from a note (label itself is NOT deleted)
 *  - mapToResponse()        : now includes the note's active (non-deleted) labels
 */
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository      noteRepository;
    private final NoteLabelRepository noteLabelRepository;

    // ── Create ────────────────────────────────────────────────────────────

    @Transactional
    public NoteResponse addNote(NoteRequest req, User owner) {
        Note note = Note.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                // State flags default: isPined=false, isArchived=false, isDeleted=false
                // They are NOT set from the request body — use dedicated UC5 endpoints.
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
            note.setArchived(false);
        }

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

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
            note.setPined(false);
        }

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

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
     * UC5 Rule: Note MUST be in trash first (two-tier deletion model).
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
     * Fallback hard-delete (backwards-compat for UC4 DELETE /notes/deleteNotes).
     * Does NOT require trash-first.
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

    // ── UC6 Label ↔ Note Association ─────────────────────────────────────

    /**
     * Attaches an existing label to a note.
     * Both the note and the label must belong to the same user.
     *
     * @throws ResourceNotFoundException  if note or label not found / wrong owner
     * @throws InvalidNoteStateException  if trying to apply a deleted label
     */
    @Transactional
    public NoteResponse addLabelToNote(Long noteId, Long labelId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        NoteLabel label = noteLabelRepository.findByIdAndOwner(labelId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Label not found with id: " + labelId));

        if (label.isDeleted()) {
            throw new InvalidNoteStateException(
                    "Cannot apply a deleted label to a note. Restore the label first.");
        }

        note.getLabels().add(label);
        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    /**
     * Detaches a label from a note.
     * The label itself is NOT deleted — it remains usable on other notes.
     *
     * @throws ResourceNotFoundException if note or label not found / wrong owner
     */
    @Transactional
    public NoteResponse removeLabelFromNote(Long noteId, Long labelId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId));

        NoteLabel label = noteLabelRepository.findByIdAndOwner(labelId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Label not found with id: " + labelId));

        // Set.remove() works correctly because NoteLabel.equals() is scoped to id
        note.getLabels().remove(label);
        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    // ── Helper ────────────────────────────────────────────────────────────

    NoteResponse mapToResponse(Note note) {
        List<NoteLabelResponse> labelResponses = note.getLabels().stream()
                .filter(l -> !l.isDeleted())          // exclude soft-deleted labels
                .map(l -> NoteLabelResponse.builder()
                        .id(l.getId())
                        .label(l.getLabel())
                        .isDeleted(l.isDeleted())
                        .userId(l.getOwner().getId())
                        .build())
                .collect(Collectors.toList());

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
                .labels(labelResponses)
                .build();
    }
}
