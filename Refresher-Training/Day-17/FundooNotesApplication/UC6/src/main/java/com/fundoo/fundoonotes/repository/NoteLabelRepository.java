package com.fundoo.fundoonotes.repository;

import com.fundoo.fundoonotes.entity.NoteLabel;
import com.fundoo.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * NoteLabelRepository — UC6
 *
 * Provides:
 *  - findByOwnerAndIsDeletedFalse   : active labels for a user (for getNoteLabelList)
 *  - findByIdAndOwner               : ownership-scoped single label lookup
 *  - existsByLabelAndOwnerAndIsDeletedFalse : uniqueness check per user before create/update
 */
@Repository
public interface NoteLabelRepository extends JpaRepository<NoteLabel, Long> {

    /**
     * Returns all non-deleted labels for a specific user.
     * Used by GET /noteLabels/getNoteLabelList.
     */
    List<NoteLabel> findByOwnerAndIsDeletedFalse(User owner);

    /**
     * Retrieves a label by its ID and verifies ownership.
     * Guarantees no cross-user label access.
     */
    Optional<NoteLabel> findByIdAndOwner(Long id, User owner);

    /**
     * Checks whether a user already has an active label with the same text.
     * Used by createLabel and updateLabel to enforce per-user uniqueness.
     *
     * Note: @Column(unique) alone would be global uniqueness — we need
     * scoped uniqueness (per user), so this is a Service-layer check.
     */
    boolean existsByLabelAndOwnerAndIsDeletedFalse(String label, User owner);
}
