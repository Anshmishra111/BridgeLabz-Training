package com.fundoo.fundoonotes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * note_checklists — individual checklist items on a note.
 * Actively used in Use Case 12; schema complete from UC1.
 */
@Entity
@Table(name = "note_checklists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @Column(name = "is_checked", nullable = false)
    @Builder.Default
    private Boolean isChecked = false;

    @Column(name = "position", nullable = false)
    @Builder.Default
    private Integer position = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "note_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_checklist_note"))
    private Note note;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
