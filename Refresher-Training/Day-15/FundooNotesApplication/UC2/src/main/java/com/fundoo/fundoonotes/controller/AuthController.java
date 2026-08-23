package com.fundoo.fundoonotes.controller;

import com.fundoo.fundoonotes.dto.AuthResponse;
import com.fundoo.fundoonotes.dto.LoginRequest;
import com.fundoo.fundoonotes.dto.RegisterRequest;
import com.fundoo.fundoonotes.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AuthController — UC2
 *
 * Endpoints (all public — no JWT required):
 *   POST /user/userSignUp  → 201 + AuthResponse
 *   POST /user/login       → 200 + AuthResponse
 *   POST /user/reset       → 200 stub (full impl in UC8 via JMS/email)
 *
 * Naming follows the API doc's endpoint convention.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // ── Register ──────────────────────────────────────────────────────────

    /**
     * POST /user/userSignUp
     * Body: { firstName, lastName, email, password }
     * Returns 201 + { token, userId, email, firstName, lastName, message }
     */
    @PostMapping("/userSignUp")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest req) {

        AuthResponse response = userService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ── Login ─────────────────────────────────────────────────────────────

    /**
     * POST /user/login
     * Body: { email, password }
     * Returns 200 + { token, userId, email, firstName, lastName, message }
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest req) {

        AuthResponse response = userService.login(req);
        return ResponseEntity.ok(response);
    }

    // ── Password Reset Stub (full impl in UC8) ────────────────────────────

    /**
     * POST /user/reset
     * Body: { email }
     * UC8 will wire this to JMS/email delivery.
     * Currently returns a 200 acknowledgement.
     */
    @PostMapping("/reset")
    public ResponseEntity<Map<String, String>> resetPassword(
            @RequestBody Map<String, String> body) {

        String email = body.getOrDefault("email", "");
        // TODO UC8: publish ResetPasswordEvent to JMS/RabbitMQ queue
        return ResponseEntity.ok(Map.of(
            "message", "If an account exists for " + email +
                       ", a reset link will be sent (available in UC8)."
        ));
    }
}
