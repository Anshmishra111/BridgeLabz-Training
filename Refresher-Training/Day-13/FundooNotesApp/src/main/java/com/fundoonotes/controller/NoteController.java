package com.fundoonotes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * A simple protected controller used to verify JWT authentication works.
 *
 * <p>Hitting GET /api/notes without a valid Bearer token returns 403.
 * With a valid token, it returns the authenticated user's ID.</p>
 */
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> getNotes(Authentication authentication) {
        return ResponseEntity.ok(Map.of(
                "message", "You are authenticated!",
                "userId", authentication.getPrincipal(),
                "notes", new String[]{
                        "Sample Note 1 — Buy groceries",
                        "Sample Note 2 — Read Spring Security docs"
                }
        ));
    }
}
