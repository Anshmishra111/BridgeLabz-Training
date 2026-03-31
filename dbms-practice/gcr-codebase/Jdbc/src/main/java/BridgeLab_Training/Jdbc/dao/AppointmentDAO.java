package BridgeLab_Training.Jdbc.dao;

import BridgeLab_Training.Jdbc.model.Appointment;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentDAO {

    boolean isSlotAvailable(int doctorId, LocalDate date);

    int bookAppointment(Appointment appointment);

    List<String> getDoctorAvailability(int doctorId, LocalDate date);

    boolean cancelAppointment(int appointmentId);

    boolean rescheduleAppointment(int appointmentId, int doctorId,
                                  LocalDate date, String time);

    List<String> getDailySchedule(LocalDate date);
}