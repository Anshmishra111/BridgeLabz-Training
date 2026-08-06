package com.clinic.dao;

import com.clinic.dto.Patient;

import java.util.List;


public interface PatientDAO {

    /**
     * Insert a new patient row.
     * @return the generated patient_id, or -1 on failure.
     */
    int insertPatient(Patient patient);

    /**
     * Fetch a single patient by primary key.
     * @return the Patient, or null if no row found.
     */
    Patient getPatientById(int patientId);

    /**
     * Fetch all patients ordered by last name, first name.
     */
    List<Patient> getAllPatients();

    /**
     * Update every updatable field of an existing patient row.
     * Uses patient.patientId as the WHERE key.
     * @return true on success, false if no row was updated.
     */
    boolean updatePatient(Patient patient);

    /**
     * Delete a patient row by ID.
     * Will fail (return false) if the patient has linked appointments.
     * @return true on success, false on failure / not found.
     */
    boolean deletePatient(int patientId);

    /**
     * Case-insensitive search by first or last name fragment.
     * e.g., searchPatientByName("ram") finds "Ramesh", "Ramya", etc.
     */
    List<Patient> searchPatientByName(String namePart);
}
