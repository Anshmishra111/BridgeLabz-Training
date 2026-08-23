package com.fundoo.fundoonotes.service;

import com.fundoo.fundoonotes.dto.NoteRequest;
import com.fundoo.fundoonotes.dto.NoteResponse;
import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.entity.TypeOfNote;
import com.fundoo.fundoonotes.exception.ResourceNotFoundException;
import com.fundoo.fundoonotes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * NoteService — UC4
 * Business logic for Notes CRUD.
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
                .isPined(req.isPined())
                .isArchived(req.isArchived())
                .isDeleted(req.isDeleted())
                .color(req.getColor())
                .typeOfNote(req.getTypeOfNote() != null ? req.getTypeOfNote() : TypeOfNote.TEXT)
                .imageUrl(req.getImageUrl())
                .linkUrl(req.getLinkUrl())
                .owner(owner)
                .build();

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    // ── Read List ─────────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<NoteResponse> getNotesList(User owner) {
        return noteRepository.findByOwnerAndIsDeletedFalse(owner)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ── Read Detail ───────────────────────────────────────────────────────

    @Transactional(readOnly = true)
    public NoteResponse getNotesDetail(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId)); // Returns 404 to avoid information leakage

        return mapToResponse(note);
    }

    // ── Update ────────────────────────────────────────────────────────────

    @Transactional
    public NoteResponse updateNote(Long noteId, NoteRequest req, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId)); // Returns 404 to avoid information leakage

        note.setTitle(req.getTitle());
        note.setDescription(req.getDescription());
        note.setPined(req.isPined());
        note.setArchived(req.isArchived());
        note.setDeleted(req.isDeleted());
        note.setColor(req.getColor());
        if (req.getTypeOfNote() != null) {
            note.setTypeOfNote(req.getTypeOfNote());
        }
        note.setImageUrl(req.getImageUrl());
        note.setLinkUrl(req.getLinkUrl());

        note = noteRepository.save(note);
        return mapToResponse(note);
    }

    // ── Delete ────────────────────────────────────────────────────────────

    @Transactional
    public void deleteNote(Long noteId, User owner) {
        Note note = noteRepository.findByNoteIdAndOwner(noteId, owner)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Note not found with id: " + noteId)); // Returns 404 to avoid information leakage

        noteRepository.delete(note);
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
