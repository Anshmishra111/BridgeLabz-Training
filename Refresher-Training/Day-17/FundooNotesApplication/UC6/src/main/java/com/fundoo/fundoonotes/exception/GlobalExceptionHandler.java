package com.fundoo.fundoonotes.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler — UC6
 *
 * Converts exceptions to clean JSON error bodies — no stack traces exposed.
 *
 * Handled exceptions:
 *   - MethodArgumentNotValidException  → 400 (validation failures)
 *   - BadCredentialsException          → 401 (wrong email/password)
 *   - IllegalStateException            → 401 (no authenticated user in context)
 *   - DuplicateEmailException          → 409 (email already registered)
 *   - DuplicateLabelException          → 409 (label already exists for this user — UC6)
 *   - ResourceNotFoundException         → 404 (not found / ownership fail)
 *   - InvalidNoteStateException        → 400 (illegal state transition)
 *   - Exception (catch-all)            → 500
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── Validation errors ─────────────────────────────────────────────────

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errorBody(
                HttpStatus.BAD_REQUEST, "Validation failed", request.getRequestURI(), fieldErrors));
    }

    // ── Wrong credentials ─────────────────────────────────────────────────

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials(
            BadCredentialsException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody(
                HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI(), null));
    }

    // ── No auth in SecurityContext ────────────────────────────────────────

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalState(
            IllegalStateException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody(
                HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI(), null));
    }

    // ── Duplicate email ───────────────────────────────────────────────────

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateEmail(
            DuplicateEmailException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody(
                HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), null));
    }

    // ── Duplicate label (UC6) ─────────────────────────────────────────────

    @ExceptionHandler(DuplicateLabelException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateLabel(
            DuplicateLabelException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody(
                HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), null));
    }

    // ── Resource not found ────────────────────────────────────────────────

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody(
                HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null));
    }

    // ── Invalid Note / Label State ────────────────────────────────────────

    @ExceptionHandler(InvalidNoteStateException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidState(
            InvalidNoteStateException ex,
            HttpServletRequest request) {

        return ResponseEntity.badRequest().body(errorBody(
                HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI(), null));
    }

    // ── Catch-all ─────────────────────────────────────────────────────────

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(
            Exception ex,
            HttpServletRequest request) {

        return ResponseEntity.internalServerError().body(errorBody(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred",
                request.getRequestURI(), null));
    }

    // ── Helper ────────────────────────────────────────────────────────────

    private Map<String, Object> errorBody(HttpStatus status, String message,
                                           String path, Map<String, String> errors) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status",    status.value());
        body.put("error",     status.getReasonPhrase());
        body.put("message",   message);
        body.put("path",      path);
        if (errors != null) body.put("fieldErrors", errors);
        return body;
    }
}
