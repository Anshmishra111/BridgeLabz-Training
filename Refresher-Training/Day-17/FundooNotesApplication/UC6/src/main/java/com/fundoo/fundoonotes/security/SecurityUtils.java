package com.fundoo.fundoonotes.security;

import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * SecurityUtils — UC3/UC6 (same as UC5).
 *
 * Centralises retrieval of the authenticated User entity from the
 * SecurityContextHolder, so controllers never accept userId as a parameter.
 */
@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    /**
     * Returns the authenticated User entity.
     *
     * @throws IllegalStateException if no authentication is present in the context
     */
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new IllegalStateException("No authenticated user found in security context");
        }

        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException(
                        "Authenticated user not found in database: " + email));
    }
}
