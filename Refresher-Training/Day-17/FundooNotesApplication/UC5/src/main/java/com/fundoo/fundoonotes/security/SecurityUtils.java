package com.fundoo.fundoonotes.security;

import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.exception.ResourceNotFoundException;
import com.fundoo.fundoonotes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * SecurityUtils — UC3
 *
 * Centralises "who is the current caller?" logic so that NO controller
 * ever reads user identity from a request parameter — always from
 * the SecurityContext (the authenticated principal).
 *
 * This is the single canonical way to get the logged-in User entity.
 */
@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    /**
     * Returns the fully-loaded User entity for the authenticated principal.
     *
     * @throws IllegalStateException     if the security context has no authentication
     * @throws ResourceNotFoundException if the email in the token no longer maps to a user
     */
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new IllegalStateException("No authenticated user in SecurityContext");
        }

        String email = auth.getName();   // set by JwtAuthFilter from the JWT subject
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Authenticated user not found: " + email));
    }

    /**
     * Returns just the email (subject) of the current principal.
     * Cheaper than a DB round-trip when you only need the email.
     */
    public String getCurrentEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user in SecurityContext");
        }
        return auth.getName();
    }
}
