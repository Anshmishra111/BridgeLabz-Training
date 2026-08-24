package com.fundoo.fundoonotes.specification;

import com.fundoo.fundoonotes.entity.Note;
import com.fundoo.fundoonotes.entity.User;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * NoteSpecification — UC7
 *
 * Builds JPA Criteria Specifications for flexible note search.
 *
 * Design rules:
 *   1. The owner predicate is UNCONDITIONAL — it is always applied first,
 *      regardless of other filters. This prevents IDOR: no search parameter
 *      can ever return notes belonging to a different user.
 *   2. All other predicates are additive (AND-combined) and optional.
 *   3. query.distinct(true) prevents duplicate rows when joining labels.
 *
 * Supported filters:
 *   titleText  — case-insensitive LIKE search on the title field
 *   state      — "active" | "pinned" | "archived" | "deleted"
 *   labelName  — exact match on a non-deleted label's text
 */
public class NoteSpecification {

    private NoteSpecification() { /* utility class — no instantiation */ }

    /**
     * General-purpose search spec.
     * Any combination of filters is supported; all are optional except owner.
     *
     * @param owner     the authenticated user (ALWAYS applied — cannot be bypassed)
     * @param titleText optional partial/case-insensitive title search
     * @param state     optional state filter: "active" | "pinned" | "archived" | "deleted"
     * @param labelName optional exact label name filter
     */
    public static Specification<Note> search(
            User   owner,
            String titleText,
            String state,
            String labelName) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // ── 1. Owner predicate — UNCONDITIONAL ─────────────────────────
            // This is the anchor that scopes every search to the current user.
            // It must be the first predicate and can never be removed by params.
            predicates.add(cb.equal(root.get("owner"), owner));

            // ── 2. Title text — case-insensitive LIKE ──────────────────────
            if (titleText != null && !titleText.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("title")),
                        "%" + titleText.toLowerCase() + "%"
                ));
            }

            // ── 3. State filter ─────────────────────────────────────────────
            if (state != null && !state.isBlank()) {
                switch (state.toLowerCase()) {
                    case "pinned"   -> predicates.add(cb.isTrue(root.get("isPined")));
                    case "archived" -> predicates.add(cb.isTrue(root.get("isArchived")));
                    case "deleted"  -> predicates.add(cb.isTrue(root.get("isDeleted")));
                    case "active"   -> {
                        predicates.add(cb.isFalse(root.get("isArchived")));
                        predicates.add(cb.isFalse(root.get("isDeleted")));
                    }
                    // unknown state values are silently ignored (no filter applied)
                }
            }

            // ── 4. Label filter — join with note_note_labels ────────────────
            if (labelName != null && !labelName.isBlank()) {
                // INNER JOIN: only notes that have this label are returned
                var labelJoin = root.join("labels", JoinType.INNER);
                predicates.add(cb.equal(labelJoin.get("label"), labelName));
                // Exclude soft-deleted labels from matching
                predicates.add(cb.isFalse(labelJoin.get("isDeleted")));
            }

            // Deduplicate — a note with N matching labels would appear N times
            // without distinct(true) (Hibernate cartesian-product behaviour).
            query.distinct(true);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Convenience spec: filter notes by a single label name.
     * Delegates to the general search spec with no title/state filter.
     * Used by GET /notes/getNotesListByLabel/{labelName}.
     */
    public static Specification<Note> byLabel(User owner, String labelName) {
        return search(owner, null, null, labelName);
    }
}
