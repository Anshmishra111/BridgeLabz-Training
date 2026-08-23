package com.fundoo.fundoonotes.repository;

import com.fundoo.fundoonotes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository — UC4
 * Provides:
 * - findByEmail : used by login and UserDetailsService
 * - existsByEmail: used during registration to detect duplicates
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
