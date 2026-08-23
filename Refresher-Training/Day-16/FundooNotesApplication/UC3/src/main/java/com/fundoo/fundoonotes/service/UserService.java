package com.fundoo.fundoonotes.service;

import com.fundoo.fundoonotes.dto.AuthResponse;
import com.fundoo.fundoonotes.dto.LoginRequest;
import com.fundoo.fundoonotes.dto.RegisterRequest;
import com.fundoo.fundoonotes.entity.User;
import com.fundoo.fundoonotes.exception.DuplicateEmailException;
import com.fundoo.fundoonotes.repository.UserRepository;
import com.fundoo.fundoonotes.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService — UC2
 *
 * Business logic for user registration and login.
 * All password handling uses BCrypt — plain text is never stored.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository  userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil         jwtUtil;

    // ── Registration ──────────────────────────────────────────────────────

    /**
     * Register a new user.
     *
     * @throws DuplicateEmailException if email is already taken
     */
    @Transactional
    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new DuplicateEmailException(
                    "Email already registered: " + req.getEmail());
        }

        User user = User.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .isVerified(false)
                .build();

        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .message("Registration successful")
                .build();
    }

    // ── Login ─────────────────────────────────────────────────────────────

    /**
     * Authenticate a user and return a JWT.
     *
     * @throws BadCredentialsException if email not found or password wrong
     */
    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadCredentialsException(
                        "Invalid email or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .message("Login successful")
                .build();
    }
}
