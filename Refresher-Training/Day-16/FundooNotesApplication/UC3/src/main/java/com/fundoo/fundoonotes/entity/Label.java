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
 * Labels / Tags — many-to-many with Note.
 *
 * ER notes (Day 2):
 *   note_labels (1) ──── (N) note_label_mapping (join) ──── (N) notes
 *
 * Actively used in Use Case 6; schema complete from UC1.
 */
@Entity
@Table(
    name = "note_labels",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_labels_user_name",
                          columnNames = {"user_id", "name"})
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /** Each label belongs to a specific user. */
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

    /** Inverse side of the many-to-many. */
    @ManyToMany(mappedBy = "labels")
    @Builder.Default
    private List<Note> notes = new ArrayList<>();
}
