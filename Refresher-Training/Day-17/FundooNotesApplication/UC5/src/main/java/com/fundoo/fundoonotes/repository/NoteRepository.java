package com.fundoo.fundoonotes.repository;

import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * NoteRepository — UC5
 * Scopes note queries by owner and note states.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Retrieve all active notes of a specific user (not archived and not deleted).
     */
    List<Note> findByOwnerAndIsArchivedFalseAndIsDeletedFalse(User owner);

    /**
     * Retrieve all archived notes of a specific user (archived but not deleted).
     */
    List<Note> findByOwnerAndIsArchivedTrueAndIsDeletedFalse(User owner);

    /**
     * Retrieve all soft-deleted / trashed notes of a specific user.
     */
    List<Note> findByOwnerAndIsDeletedTrue(User owner);

    /**
     * Retrieve a specific note by its ID and owner.
     * Guarantees ownership scoping directly at database level.
     */
    Optional<Note> findByNoteIdAndOwner(Long noteId, User owner);
}
