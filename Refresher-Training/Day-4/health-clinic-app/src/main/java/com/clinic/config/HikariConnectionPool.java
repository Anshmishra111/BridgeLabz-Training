package com.clinic.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class HikariConnectionPool {

    // -------------------------------------------------------
    // Database credentials — update before running
    // -------------------------------------------------------
    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/health_clinic_db"
        + "?useSSL=false"
        + "&serverTimezone=UTC"
        + "&allowPublicKeyRetrieval=true"
        + "&characterEncoding=UTF-8";

    private static final String DB_USERNAME = "clinic_app_user";
    private static final String DB_PASSWORD = "StrongPassword123!";

    // -------------------------------------------------------
    // Pool configuration
    // -------------------------------------------------------
    private static final int POOL_MAX_SIZE    = 10; // max simultaneous connections
    private static final int POOL_MIN_IDLE    = 2;  // keep 2 warm at all times
    private static final int CONN_TIMEOUT_MS  = 30_000; // wait up to 30s for a free connection
    private static final int IDLE_TIMEOUT_MS  = 600_000; // idle connections evicted after 10 min
    private static final int MAX_LIFETIME_MS  = 1_800_000; // connections recycled after 30 min

    // -------------------------------------------------------
    // Singleton DataSource (created once, reused forever)
    // -------------------------------------------------------
    private static final HikariDataSource DATA_SOURCE;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);

        config.setMaximumPoolSize(POOL_MAX_SIZE);
        config.setMinimumIdle(POOL_MIN_IDLE);
        config.setConnectionTimeout(CONN_TIMEOUT_MS);
        config.setIdleTimeout(IDLE_TIMEOUT_MS);
        config.setMaxLifetime(MAX_LIFETIME_MS);

        // Pool name for logging / monitoring
        config.setPoolName("ClinicPool");

        // Keep-alive query — ensures idle connections stay valid
        config.setConnectionTestQuery("SELECT 1");

        DATA_SOURCE = new HikariDataSource(config);
    }

    // -------------------------------------------------------
    // Private constructor — this class is never instantiated
    // -------------------------------------------------------
    private HikariConnectionPool() {}

    /**
     * Hand out a connection from the pool.
     *
     * Every DAO and the Service layer call this method.
     * The caller MUST close the connection (or use try-with-resources)
     * to return it to the pool — it is never physically destroyed.
     *
     * @return a pooled Connection ready to use
     * @throws SQLException if no connection could be obtained
     */
    public static Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    /**
     * Gracefully shuts down the pool.
     * Call this only when the application is exiting.
     */
    public static void close() {
        if (DATA_SOURCE != null && !DATA_SOURCE.isClosed()) {
            DATA_SOURCE.close();
        }
    }
}
