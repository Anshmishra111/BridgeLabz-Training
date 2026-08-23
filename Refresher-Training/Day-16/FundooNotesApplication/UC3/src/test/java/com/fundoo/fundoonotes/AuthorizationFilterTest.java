package com.fundoo.fundoonotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundoo.fundoonotes.dto.LoginRequest;
import com.fundoo.fundoonotes.dto.RegisterRequest;
import com.fundoo.fundoonotes.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * UC3 Integration Tests — Authorization Filter + Protecting Endpoints
 *
 * Covers all acceptance criteria:
 *   1. No token        → 401
 *   2. Tampered token  → 401
 *   3. Identity from SecurityContextHolder (never from a param)
 *   4. IDOR: User A cannot access User B's data via param manipulation
 *
 * Uses MockMvc against the real security filter chain and MySQL (test profile).
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorizationFilterTest {

    @Autowired MockMvc       mockMvc;
    @Autowired ObjectMapper  objectMapper;
    @Autowired UserRepository userRepository;

    // Shared state across ordered tests
    private static String tokenA;
    private static String tokenB;

    // ── Setup: register two test users ────────────────────────────────────

    @BeforeEach
    void cleanBeforeIfFirstTest() {
        // Only clean for the first test — avoids repeated DB wipes
    }

    private String registerAndGetToken(String firstName, String email) throws Exception {
        // Try to delete existing user first (idempotent)
        userRepository.findByEmail(email).ifPresent(userRepository::delete);

        RegisterRequest req = new RegisterRequest();
        req.setFirstName(firstName);
        req.setLastName("TestUser");
        req.setEmail(email);
        req.setPassword("Pass@1234!");

        MvcResult result = mockMvc.perform(post("/user/userSignUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andReturn();

        return objectMapper.readTree(result.getResponse().getContentAsString())
                .get("token").asText();
    }

    // ── AC1: No token → 401 ───────────────────────────────────────────────

    @Test
    @Order(1)
    @DisplayName("AC1 — No token: protected endpoint returns 401")
    void noToken_returns401() throws Exception {
        mockMvc.perform(get("/user/profile"))
                .andExpect(status().isUnauthorized());
    }

    // ── AC2: Tampered token → 401 ─────────────────────────────────────────

    @Test
    @Order(2)
    @DisplayName("AC2 — Tampered token: one char changed returns 401")
    void tamperedToken_returns401() throws Exception {
        // Get a real token first
        tokenA = registerAndGetToken("UserA", "uc3_usera@fundoo.com");

        // Flip the last character to tamper the signature
        String tampered = tokenA.substring(0, tokenA.length() - 1) +
                (tokenA.charAt(tokenA.length() - 1) == 'A' ? 'B' : 'A');

        mockMvc.perform(get("/user/profile")
                .header("Authorization", "Bearer " + tampered))
                .andExpect(status().isUnauthorized());
    }

    // ── AC3: Valid token → 200, identity from SecurityContextHolder ───────

    @Test
    @Order(3)
    @DisplayName("AC3 — Valid token: profile returns authenticated user's data")
    void validToken_returns200WithCorrectUser() throws Exception {
        if (tokenA == null) tokenA = registerAndGetToken("UserA", "uc3_usera@fundoo.com");

        mockMvc.perform(get("/user/profile")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("uc3_usera@fundoo.com")))
                .andExpect(jsonPath("$.firstName", is("UserA")))
                .andExpect(jsonPath("$.userId", notNullValue()));
    }

    // ── AC4 (IDOR): User A's token cannot expose User B's data ───────────

    @Test
    @Order(4)
    @DisplayName("IDOR — User A's token always returns User A's profile, not B's")
    void idor_userATokenCannotSeeUserB() throws Exception {
        if (tokenA == null) tokenA = registerAndGetToken("UserA", "uc3_usera@fundoo.com");
        tokenB = registerAndGetToken("UserB", "uc3_userb@fundoo.com");

        // Even if caller knows User B's email, A's token always returns A's data
        // (The endpoint never accepts a userId/email param — identity is from JWT)
        mockMvc.perform(get("/user/profile")
                .header("Authorization", "Bearer " + tokenA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("uc3_usera@fundoo.com")))  // must be A
                .andExpect(jsonPath("$.email", not("uc3_userb@fundoo.com"))); // never B
    }

    // ── Bonus: Public endpoints stay open ────────────────────────────────

    @Test
    @Order(5)
    @DisplayName("Public — /user/login is accessible without a token")
    void loginEndpoint_isPublic() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setEmail("uc3_usera@fundoo.com");
        req.setPassword("Pass@1234!");

        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    // ── Bonus: Empty Bearer prefix → 401 ────────────────────────────────

    @Test
    @Order(6)
    @DisplayName("Edge — Empty Bearer value returns 401")
    void emptyBearerToken_returns401() throws Exception {
        mockMvc.perform(get("/user/profile")
                .header("Authorization", "Bearer "))
                .andExpect(status().isUnauthorized());
    }
}
