package com.clinic.dto;

/**
 * DTO — Floor 1 Data Box for the specializations table.
 * Mirrors every column in the specializations table exactly.
 * No SQL, no business logic — just fields + getters/setters.
 */
public class Specialization {

    private int    specializationId;
    private String name;
    private String description;

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public Specialization() {}

    public Specialization(String name, String description) {
        this.name        = name;
        this.description = description;
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public int getSpecializationId()                        { return specializationId; }
    public void setSpecializationId(int specializationId)   { this.specializationId = specializationId; }

    public String getName()                 { return name; }
    public void setName(String name)        { this.name = name; }

    public String getDescription()                  { return description; }
    public void setDescription(String description)  { this.description = description; }

    // -------------------------------------------------------
    // toString
    // -------------------------------------------------------
    @Override
    public String toString() {
        return String.format(
            "Specialization[ID=%-3d | %-25s | %s]",
            specializationId, name,
            (description != null ? description : "No description")
        );
    }
}
