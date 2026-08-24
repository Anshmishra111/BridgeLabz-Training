package com.fundoo.fundoonotes.controller;

import com.fundoo.fundoonotes.dto.UserProfileResponse;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController — UC3
 *
 * Protected endpoints that require a valid JWT.
 *
 * UC3 Core Rule: User identity is ALWAYS derived from SecurityContextHolder
 * via SecurityUtils — NEVER from a path variable or request parameter.
 * This prevents IDOR (Insecure Direct Object Reference) vulnerabilities.
 *
 * Endpoints:
 *   GET /user/profile → 200 { userId, email, firstName, lastName }
 *                         401 if no token / tampered token
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SecurityUtils securityUtils;

    /**
     * GET /user/profile
     *
     * Returns the profile of the currently authenticated user.
     * Identity comes exclusively from SecurityContextHolder — never a param.
     *
     * Protected: requires Authorization: Bearer <token>
     */
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile() {
        User user = securityUtils.getCurrentUser();

        return ResponseEntity.ok(UserProfileResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isVerified(user.getIsVerified())
                .build());
    }
}
