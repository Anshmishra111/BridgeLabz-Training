package com.clinic.dao;

import com.clinic.dto.Doctor;
import com.clinic.dto.Specialization;

import java.util.List;

/**
 * DoctorDAO — contract for the doctors table + doctor_specializations join.
 */
public interface DoctorDAO {

    /** Insert a new doctor. @return generated doctor_id, or -1 on failure. */
    int insertDoctor(Doctor doctor);

    /** @return the Doctor, or null if not found. */
    Doctor getDoctorById(int doctorId);

    /** @return all doctors ordered by last name, first name. */
    List<Doctor> getAllDoctors();

    /** Update all updatable fields. @return true on success. */
    boolean updateDoctor(Doctor doctor);

    /** Delete a doctor. Fails (false) if doctor has linked appointments. */
    boolean deleteDoctor(int doctorId);

    /**
     * Assign a specialization to a doctor (insert into doctor_specializations).
     * Silently succeeds if the link already exists (INSERT IGNORE).
     */
    boolean assignSpecialization(int doctorId, int specializationId);

    /**
     * Remove one specialization from a doctor.
     */
    boolean removeSpecialization(int doctorId, int specializationId);

    /**
     * Get all specializations assigned to a specific doctor.
     */
    List<Specialization> getDoctorSpecializations(int doctorId);
}
