package com.fundoonotes.controller;

import com.fundoonotes.dto.AuthResponse;
import com.fundoonotes.dto.LoginRequest;
import com.fundoonotes.dto.RegisterRequest;
import com.fundoonotes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication controller for user registration and login.
 *
 * <p>Both endpoints are publicly accessible (configured in SecurityConfig).</p>
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /auth/register — creates a new user account.
     *
     * @return 201 Created with a JWT token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        String token = userService.register(
                request.getEmail(),
                request.getPassword(),
                request.getName());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new AuthResponse(token));
    }

    /**
     * POST /auth/login — authenticates a user.
     *
     * @return 200 OK with a JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {

        String token = userService.login(
                request.getEmail(),
                request.getPassword());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
