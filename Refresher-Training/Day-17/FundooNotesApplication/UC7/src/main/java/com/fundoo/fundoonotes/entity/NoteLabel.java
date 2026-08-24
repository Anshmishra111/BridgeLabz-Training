package com.fundoo.fundoonotes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * NoteLabel — UC6
 *
 * Represents a user-owned tag/label that can be applied to multiple notes.
 *
 * API doc shape: { "label": "string", "isDeleted": true, "id": "string", "userId": "string" }
 *
 * Key design decisions:
 *  - "label" field name matches the API doc exactly (not "name")
 *  - isDeleted is a soft-delete flag — deleted labels stay in the DB but
 *    are excluded from getNoteLabelList.
 *  - @EqualsAndHashCode is scoped to id only — required for correct
 *    Set.remove() behaviour when detaching a label from a note's Set<NoteLabel>.
 */
@Entity
@Table(name = "note_labels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"owner", "notes"})
public class NoteLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * The label text. Uniqueness per user is enforced in the Service layer
     * (not via @Column(unique) which would be global, not per-user).
     */
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "label", nullable = false, length = 50)
    private String label;

    /**
     * Soft-delete flag. Deleted labels are hidden from getNoteLabelList
     * but remain in the database for historical associations.
     */
    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = false;

    /** Label belongs to a specific user. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_labels_user"))
    private User owner;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /** Inverse side of the many-to-many with Note. */
    @ManyToMany(mappedBy = "labels")
    @Builder.Default
    private Set<Note> notes = new HashSet<>();
}
