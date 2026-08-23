package com.fundoo.fundoonotes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Notes table.
 *
 * ER notes (Day 2):
 *   users  (1) ──── (N) notes
 *   notes  (1) ──── (N) note_labels       (join table: note_label_mapping)
 *   notes  (1) ──── (N) note_checklists
 *
 * State flags (pin / archive / trash) are added here so the schema is
 * ready when Use Case 5 arrives.
 */
@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    /** Background colour stored as a hex string, e.g. "#FFFFFF". */
    @Column(name = "color", length = 20)
    @Builder.Default
    private String color = "#FFFFFF";

    // ── State flags (UC 5) ──────────────────────────────────────────────
    @Column(name = "is_pinned", nullable = false)
    @Builder.Default
    private Boolean isPinned = false;

    @Column(name = "is_archived", nullable = false)
    @Builder.Default
    private Boolean isArchived = false;

    @Column(name = "is_trashed", nullable = false)
    @Builder.Default
    private Boolean isTrashed = false;

    /** Reminder timestamp — used from Use Case 8. */
    @Column(name = "reminder_at")
    private LocalDateTime reminderAt;

    // ── Audit ────────────────────────────────────────────────────────────
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Relationships ────────────────────────────────────────────────────

    /** Owning user — the foreign key lives in this table. */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_notes_user"))
    private User owner;

    /**
     * Many-to-many with Label via a join table.
     * Added here to complete the ER diagram (Day 2); actively used in UC 6.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "note_label_mapping",
        joinColumns        = @JoinColumn(name = "note_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id"),
        foreignKey         = @ForeignKey(name = "fk_nlm_note"),
        inverseForeignKey  = @ForeignKey(name = "fk_nlm_label")
    )
    @Builder.Default
    private List<Label> labels = new ArrayList<>();

    /**
     * One-to-many with ChecklistItem.
     * Defined here; actively used in Use Case 12.
     */
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChecklistItem> checklistItems = new ArrayList<>();
}
