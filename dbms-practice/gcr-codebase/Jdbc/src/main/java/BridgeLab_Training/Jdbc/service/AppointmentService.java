package BridgeLab_Training.Jdbc.service;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    int bookAppointment(int patientId, int doctorId,
                        LocalDate date, String time);

    List<String> checkAvailability(int doctorId, LocalDate date);

    void cancelAppointment(int appointmentId);

    void rescheduleAppointment(int appointmentId, int doctorId,
                               LocalDate date, String time);

    List<String> viewDailySchedule(LocalDate date);
}