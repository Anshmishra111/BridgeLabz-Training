package com.fundoonotes.service;

import com.fundoonotes.entity.User;
import com.fundoonotes.repository.UserRepository;
import com.fundoonotes.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Handles user registration and login.
 *
 * <p>Registration checks for duplicate emails via {@code findByEmail}
 * before inserting. If that guard is removed (Problem 5), the
 * database's UNIQUE constraint will throw a
 * {@code DataIntegrityViolationException} instead.</p>
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Registers a new user.
     *
     * @return JWT token for the newly created user
     * @throws IllegalArgumentException if the email is already registered
     */
    public String register(String email, String password, String name) {
        // Guard: check for duplicate email (Problem 5)
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setName(name);

        User saved = userRepository.save(user);

        return jwtUtil.generateToken(
                String.valueOf(saved.getUserId()),
                saved.getEmail());
    }

    /**
     * Authenticates a user and returns a JWT.
     *
     * @return JWT token if credentials are valid
     * @throws IllegalArgumentException if email not found or password mismatch
     */
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return jwtUtil.generateToken(
                String.valueOf(user.getUserId()),
                user.getEmail());
    }
}
