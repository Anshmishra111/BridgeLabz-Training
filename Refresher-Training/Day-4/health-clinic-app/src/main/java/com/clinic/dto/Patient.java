package com.clinic.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO — Floor 1 Data Box for the patients table.
 * Matches user's schema exactly:
 *   patient_id, first_name, last_name, date_of_birth, gender,
 *   phone_number, email, is_active, registered_on
 */
public class Patient {

    private int           patientId;
    private String        firstName;
    private String        lastName;
    private LocalDate     dateOfBirth;
    private String        gender;        // 'Male' | 'Female' | 'Other'
    private String        phoneNumber;   // column: phone_number
    private String        email;
    private boolean       isActive;      // column: is_active
    private LocalDateTime registeredOn;  // column: registered_on

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public Patient() {
        this.isActive = true; // default
    }

    /** Quick registration — minimum required fields. */
    public Patient(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }

    /** Full constructor. */
    public Patient(String firstName, String lastName, LocalDate dateOfBirth,
                   String gender, String phoneNumber, String email) {
        this();
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender      = gender;
        this.phoneNumber = phoneNumber;
        this.email       = email;
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public int getPatientId()               { return patientId; }
    public void setPatientId(int id)        { this.patientId = id; }

    public String getFirstName()                    { return firstName; }
    public void setFirstName(String firstName)      { this.firstName = firstName; }

    public String getLastName()                     { return lastName; }
    public void setLastName(String lastName)        { this.lastName = lastName; }

    public LocalDate getDateOfBirth()               { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dob)       { this.dateOfBirth = dob; }

    public String getGender()               { return gender; }
    public void setGender(String gender)    { this.gender = gender; }

    public String getPhoneNumber()                  { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber)  { this.phoneNumber = phoneNumber; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public boolean isActive()               { return isActive; }
    public void setActive(boolean active)   { this.isActive = active; }

    public LocalDateTime getRegisteredOn()              { return registeredOn; }
    public void setRegisteredOn(LocalDateTime r)        { this.registeredOn = r; }

    @Override
    public String toString() {
        return String.format(
            "Patient[ID=%-4d | %-15s %-15s | %s | Phone: %-13s | Email: %-30s | Active: %s]",
            patientId, firstName, lastName,
            (gender      != null ? gender      : "N/A"),
            (phoneNumber != null ? phoneNumber : "N/A"),
            (email       != null ? email       : "N/A"),
            isActive ? "Yes" : "No"
        );
    }
}
