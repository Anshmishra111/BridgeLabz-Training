package com.clinic.dao;

import com.clinic.dto.Specialization;
import java.util.List;

/**
 * SpecializationDAO — contract for the specializations table.
 */
public interface SpecializationDAO {

    /** @return generated specialization_id, or -1 on failure. */
    int insertSpecialization(Specialization specialization);

    /** @return the Specialization, or null if not found. */
    Specialization getSpecializationById(int id);

    /** @return all specializations ordered alphabetically. */
    List<Specialization> getAllSpecializations();

    /** @return true if update succeeded. */
    boolean updateSpecialization(Specialization specialization);

    /** @return true if delete succeeded (fails if doctors are linked). */
    boolean deleteSpecialization(int id);
}
