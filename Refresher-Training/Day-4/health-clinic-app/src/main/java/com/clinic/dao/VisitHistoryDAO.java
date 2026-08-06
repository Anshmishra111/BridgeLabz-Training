package com.clinic.dao;

import com.clinic.dto.VisitHistory;
import java.sql.Connection;
import java.util.List;


public interface VisitHistoryDAO {

    /**
     * Insert a visit record using a shared transaction connection.
     * Does NOT commit — caller owns commit/rollback.
     */
    int insertVisitHistory(VisitHistory visitHistory, Connection conn);

    /** @return VisitHistory for the given appointment, or null. */
    VisitHistory getVisitByAppointment(int appointmentId);

    /** @return all visits for a patient (via appointments JOIN). */
    List<VisitHistory> getVisitsByPatient(int patientId);

    /** @return all visit records, newest first. */
    List<VisitHistory> getAllVisitHistory();
}
