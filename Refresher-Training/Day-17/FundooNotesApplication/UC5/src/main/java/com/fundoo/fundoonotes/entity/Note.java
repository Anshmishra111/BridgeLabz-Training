package com.fundoo.fundoonotes.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Notes table — UC4 Revision.
 *
 * Scoped fields matching real Fundoo notes model shape:
 * - noteId: primary key
 * - title
 * - description (not content)
 * - isPined (our doc's spelling)
 * - isArchived
 * - isDeleted (not isTrashed)
 * - color
 * - typeOfNote (Enum TEXT, CHECKLIST, IMAGE, LINK)
 * - imageUrl
 * - linkUrl
 * - owner (mapped by user_id)
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
    @Column(name = "note_id")
    private Long noteId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_pined", nullable = false)
    @Builder.Default
    private boolean isPined = false;

    @Column(name = "is_archived", nullable = false)
    @Builder.Default
    private boolean isArchived = false;

    @Column(name = "is_deleted", nullable = false)
    @Builder.Default
    private boolean isDeleted = false;

    @Column(name = "color", length = 50)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_note", length = 20)
    @Builder.Default
    private TypeOfNote typeOfNote = TypeOfNote.TEXT;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "link_url", length = 500)
    private String linkUrl;

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

    /** Owning user — foreign key user_id */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_notes_user"))
    private User owner;

    /**
     * Many-to-many with Label via a join table.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "note_label_mapping",
        joinColumns        = @JoinColumn(name = "note_id", referencedColumnName = "note_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id"),
        foreignKey         = @ForeignKey(name = "fk_nlm_note"),
        inverseForeignKey  = @ForeignKey(name = "fk_nlm_label")
    )
    @Builder.Default
    private List<Label> labels = new ArrayList<>();

    /**
     * One-to-many with ChecklistItem.
     */
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChecklistItem> checklistItems = new ArrayList<>();
}
