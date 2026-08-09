package com.greetings.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Greeting model — represents a single greeting entry.
 * Fields: id (auto), name (sender), message (greeting text), createdAt (timestamp).
 */
public class Greeting {

    private int id;
    private String name;
    private String message;
    private LocalDateTime createdAt;

    // ─── Constructors ─────────────────────────────────────────────────────────

    public Greeting() {
    }

    public Greeting(int id, String name, String message) {
        this.id        = id;
        this.name      = name;
        this.message   = message;
        this.createdAt = LocalDateTime.now();
    }

    // ─── Getters & Setters ────────────────────────────────────────────────────

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Convenience method: returns formatted timestamp for display in JSP.
     */
    public String getFormattedDate() {
        if (createdAt == null) return "";
        return createdAt.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));
    }

    @Override
    public String toString() {
        return "Greeting{id=" + id + ", name='" + name + "', message='" + message + "'}";
    }
}
