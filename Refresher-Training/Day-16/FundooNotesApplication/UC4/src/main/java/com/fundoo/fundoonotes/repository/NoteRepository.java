package com.fundoo.fundoonotes.repository;

import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * NoteRepository — UC4
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Retrieve all non-deleted notes of a specific user.
     */
    List<Note> findByOwnerAndIsDeletedFalse(User owner);

    /**
     * Retrieve a specific note by its ID and owner.
     * Guarantees ownership scoping directly at database level.
     */
    Optional<Note> findByNoteIdAndOwner(Long noteId, User owner);
}
