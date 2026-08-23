package com.fundoo.fundoonotes.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Users table — UC2 revision.
 *
 * Change from UC1: fullName split into firstName + lastName
 * to match the API doc's user model { firstName, lastName, email, id }.
 */
@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_email", columnNames = "email")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 60)
    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 60)
    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    /**
     * BCrypt hash — never store plain text (UC2).
     */
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    /** Email-verified flag — used from UC2 onward. */
    @Column(name = "is_verified", nullable = false)
    @Builder.Default
    private Boolean isVerified = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ── Relationships (schema ready, actively used in later UCs) ─────────

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Note> notes = new ArrayList<>();
}
