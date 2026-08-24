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
import com.fundoo.fundoonotes.specification.NoteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * NoteService — UC7
 *
 * UC7 additions over UC6:
 *  - searchNotes()          : general search — title, state, label (any combination)
 *  - getNotesListByLabel()  : convenience search by label name only
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
                .color(req.getColor())
                .typeOfNote(req.getTypeOfNote() != null ? req.getTypeOfNote() : TypeOfNote.TEXT)
                .imageUrl(req.getImageUrl())
                .linkUrl(req.getLinkUrl())
                .owner(owner)
                .build();
        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    // ── Read ──────────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesList(User owner) {
        return noteRepository.findByOwnerAndIsArchivedFalseAndIsDeletedFalse(owner)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NoteResponse getNotesDetail(Long noteId, User owner) {
        return mapToResponse(noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId)));
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
        note.setTitle(req.getTitle());
        note.setDescription(req.getDescription());
        note.setColor(req.getColor());
        if (req.getTypeOfNote() != null) note.setTypeOfNote(req.getTypeOfNote());
        note.setImageUrl(req.getImageUrl());
        note.setLinkUrl(req.getLinkUrl());
        return mapToResponse(noteRepository.save(note));
    }

    // ── UC5 State ─────────────────────────────────────────────────────────

    @Transactional
    public NoteResponse pinUnpinNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        if (note.isDeleted()) throw new InvalidNoteStateException("Cannot pin a trashed note");
        note.setPined(!note.isPined());
        if (note.isPined()) note.setArchived(false);
        return mapToResponse(noteRepository.save(note));
    }

    @Transactional
    public NoteResponse archiveNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        if (note.isDeleted()) throw new InvalidNoteStateException("Cannot archive a trashed note");
        note.setArchived(!note.isArchived());
        if (note.isArchived()) note.setPined(false);
        return mapToResponse(noteRepository.save(note));
    }

    @Transactional
    public NoteResponse trashNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        note.setDeleted(!note.isDeleted());
        if (note.isDeleted()) { note.setPined(false); note.setArchived(false); }
        return mapToResponse(noteRepository.save(note));
    }

    @Transactional
    public void deleteForeverNotes(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        if (!note.isDeleted()) {
            throw new InvalidNoteStateException(
                    "Note must be in Trash before it can be permanently deleted.");
        }
        noteRepository.delete(note);
    }

    @Transactional
    public void deleteNote(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        noteRepository.delete(note);
    }

    @Transactional(readOnly = true)
    public List<NoteResponse> getArchiveNotesList(User owner) {
        return noteRepository.findByOwnerAndIsArchivedTrueAndIsDeletedFalse(owner)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NoteResponse> getTrashNotesList(User owner) {
        return noteRepository.findByOwnerAndIsDeletedTrue(owner)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // ── UC6 Label ↔ Note ─────────────────────────────────────────────────

    @Transactional
    public NoteResponse addLabelToNote(Long noteId, Long labelId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        NoteLabel label = noteLabelRepository.findByIdAndOwner(labelId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found: " + labelId));
        if (label.isDeleted()) {
            throw new InvalidNoteStateException("Cannot apply a deleted label to a note.");
        }
        note.getLabels().add(label);
        return mapToResponse(noteRepository.save(note));
    }

    @Transactional
    public NoteResponse removeLabelFromNote(Long noteId, Long labelId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found: " + noteId));
        NoteLabel label = noteLabelRepository.findByIdAndOwner(labelId, owner)
                .orElseThrow(() -> new ResourceNotFoundException("Label not found: " + labelId));
        note.getLabels().remove(label);
        return mapToResponse(noteRepository.save(note));
    }

    // ── UC7 Search & Filter ───────────────────────────────────────────────

    /**
     * General-purpose note search using JPA Specification.
     *
     * The owner predicate is UNCONDITIONALLY the first predicate in the spec —
     * no combination of filter parameters can bypass it or expose other users' notes.
     *
     * @param owner     authenticated user (never from a request param)
     * @param titleText optional partial title match (case-insensitive)
     * @param state     optional: "active" | "pinned" | "archived" | "deleted"
     * @param labelName optional exact label name filter
     */
    @Transactional(readOnly = true)
    public List<NoteResponse> searchNotes(User owner, String titleText,
                                          String state, String labelName) {
        return noteRepository
                .findAll(NoteSpecification.search(owner, titleText, state, labelName))
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Convenience search: returns all active notes tagged with a specific label.
     * Delegates to NoteSpecification.byLabel().
     * Maps to GET /notes/getNotesListByLabel/{labelName}.
     */
    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesListByLabel(User owner, String labelName) {
        return noteRepository
                .findAll(NoteSpecification.byLabel(owner, labelName))
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── Helper ────────────────────────────────────────────────────────────

    NoteResponse mapToResponse(Note note) {
        List<NoteLabelResponse> labelResponses = note.getLabels().stream()
                .filter(l -> !l.isDeleted())
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
