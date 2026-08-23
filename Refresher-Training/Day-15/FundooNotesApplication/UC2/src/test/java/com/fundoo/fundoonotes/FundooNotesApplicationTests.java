package com.fundoo.fundoonotes;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * UC1 Smoke Test
 *
 * Verifies that:
 *   1. The Spring ApplicationContext loads without errors.
 *   2. Hibernate connects to the database and runs DDL successfully.
 *
 * Run with:  mvn test
 */
@SpringBootTest
@ActiveProfiles("test")
class FundooNotesApplicationTests {

    /**
     * If this test passes the entire Spring context (including DataSource
     * and Hibernate auto-DDL) initialised without exceptions.
     */
    @Test
    void contextLoads() {
        // No assertions needed — a successful load is the assertion.
    }
}
