package com.fundoo.fundoonotes.repository;

import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * NoteRepository — UC7
 *
 * UC7 addition: extends JpaSpecificationExecutor<Note> to enable
 * dynamic Criteria-API queries via NoteSpecification.
 *
 * No new method stubs needed — Specification queries are dispatched
 * through findAll(Specification<Note>) inherited from JpaSpecificationExecutor.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long>,
                                         JpaSpecificationExecutor<Note> {

    List<Note> findByOwnerAndIsArchivedFalseAndIsDeletedFalse(User owner);

    List<Note> findByOwnerAndIsArchivedTrueAndIsDeletedFalse(User owner);

    List<Note> findByOwnerAndIsDeletedTrue(User owner);

    Optional<Note> findByNoteIdAndOwner(Long noteId, User owner);
}
