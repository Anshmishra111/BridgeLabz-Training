package com.greetings.dao;

import com.greetings.model.Greeting;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In-memory Data Access Object for Greeting entities.
 * Uses a ConcurrentHashMap as the data store and AtomicInteger for auto-increment IDs.
 * Pre-seeded with sample greetings so the app has data on first launch.
 */
@Repository
public class GreetingDAO {

    // ─── In-memory data store ────────────────────────────────────────────────
    private final Map<Integer, Greeting> store = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    // ─── Constructor: seed sample data ───────────────────────────────────────
    public GreetingDAO() {
        save(new Greeting(0, "Alice",   "Good morning! Hope you have a wonderful day ahead!"));
        save(new Greeting(0, "Bob",     "Hello there! Wishing you all the best on your journey!"));
        save(new Greeting(0, "Charlie", "Happy coding! May your bugs be few and your builds be fast!"));
    }

    // ─── CREATE ──────────────────────────────────────────────────────────────

    /**
     * Persists a new greeting. Assigns a new auto-incremented ID and sets createdAt.
     *
     * @param greeting the greeting to save (id is ignored and replaced)
     * @return the saved greeting with its assigned id
     */
    public Greeting save(Greeting greeting) {
        int newId = idCounter.incrementAndGet();
        greeting.setId(newId);
        if (greeting.getCreatedAt() == null) {
            greeting.setCreatedAt(LocalDateTime.now());
        }
        store.put(newId, greeting);
        return greeting;
    }

    // ─── READ ────────────────────────────────────────────────────────────────

    /**
     * Returns all greetings sorted by ID descending (newest first).
     */
    public List<Greeting> findAll() {
        List<Greeting> list = new ArrayList<>(store.values());
        list.sort((a, b) -> Integer.compare(b.getId(), a.getId()));
        return list;
    }

    /**
     * Finds a greeting by its ID.
     *
     * @param id the greeting ID
     * @return the Greeting, or null if not found
     */
    public Greeting findById(int id) {
        return store.get(id);
    }

    // ─── UPDATE ──────────────────────────────────────────────────────────────

    /**
     * Updates an existing greeting's name and message.
     * The id and createdAt are preserved from the original record.
     *
     * @param updated the greeting with updated fields (must have a valid id)
     * @return the updated greeting, or null if id not found
     */
    public Greeting update(Greeting updated) {
        Greeting existing = store.get(updated.getId());
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setMessage(updated.getMessage());
        store.put(existing.getId(), existing);
        return existing;
    }

    // ─── DELETE ──────────────────────────────────────────────────────────────

    /**
     * Deletes a greeting by its ID.
     *
     * @param id the ID of the greeting to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(int id) {
        return store.remove(id) != null;
    }

    // ─── Count ───────────────────────────────────────────────────────────────

    /**
     * Returns the total number of greetings stored.
     */
    public int count() {
        return store.size();
    }
}
