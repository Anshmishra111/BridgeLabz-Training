package com.clinic.dto;

/**
 * DTO — Floor 1 Data Box for the doctors table.
 * Matches user's schema exactly:
 *   doctor_id, first_name, last_name, phone_number, email, is_active
 */
public class Doctor {

    private int     doctorId;
    private String  firstName;
    private String  lastName;
    private String  phoneNumber;  // column: phone_number
    private String  email;
    private boolean isActive;     // column: is_active

    // -------------------------------------------------------
    // Constructors
    // -------------------------------------------------------

    public Doctor() {
        this.isActive = true;
    }

    public Doctor(String firstName, String lastName, String phoneNumber, String email) {
        this();
        this.firstName   = firstName;
        this.lastName    = lastName;
        this.phoneNumber = phoneNumber;
        this.email       = email;
    }

    // -------------------------------------------------------
    // Getters & Setters
    // -------------------------------------------------------

    public int getDoctorId()                { return doctorId; }
    public void setDoctorId(int doctorId)   { this.doctorId = doctorId; }

    public String getFirstName()                { return firstName; }
    public void setFirstName(String firstName)  { this.firstName = firstName; }

    public String getLastName()                 { return lastName; }
    public void setLastName(String lastName)    { this.lastName = lastName; }

    public String getPhoneNumber()                  { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber)  { this.phoneNumber = phoneNumber; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public boolean isActive()               { return isActive; }
    public void setActive(boolean active)   { this.isActive = active; }

    @Override
    public String toString() {
        return String.format(
            "Doctor[ID=%-4d | Dr. %-15s %-15s | Phone: %-13s | Email: %-30s | Active: %s]",
            doctorId, firstName, lastName,
            (phoneNumber != null ? phoneNumber : "N/A"),
            (email       != null ? email       : "N/A"),
            isActive ? "Yes" : "No"
        );
    }
}
