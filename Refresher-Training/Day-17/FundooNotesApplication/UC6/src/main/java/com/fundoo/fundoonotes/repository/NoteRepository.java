package com.fundoo.fundoonotes.repository;

import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * NoteRepository — UC6 (same as UC5).
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerAndIsArchivedFalseAndIsDeletedFalse(User owner);

    List<Note> findByOwnerAndIsArchivedTrueAndIsDeletedFalse(User owner);

    List<Note> findByOwnerAndIsDeletedTrue(User owner);

    Optional<Note> findByNoteIdAndOwner(Long noteId, User owner);
}
