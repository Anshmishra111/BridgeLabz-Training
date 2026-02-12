package BridgeLab_Training.Jdbc.service;

import BridgeLab_Training.Jdbc.dao.AppointmentDAO;
import BridgeLab_Training.Jdbc.dao.AppointmentDAOImpl;
import BridgeLab_Training.Jdbc.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentDAO dao = new AppointmentDAOImpl();

    @Override
    public int bookAppointment(int patientId, int doctorId,
                               LocalDate date, String time) {

        if (!dao.isSlotAvailable(doctorId, date)) {
            throw new RuntimeException("Doctor not available");
        }

        Appointment appt = new Appointment(
                patientId, doctorId, date, LocalTime.parse(time)
        );

        return dao.bookAppointment(appt);
    }

    @Override
    public List<String> checkAvailability(int doctorId, LocalDate date) {
        return dao.getDoctorAvailability(doctorId, date);
    }

    @Override
    public void cancelAppointment(int appointmentId) {
        dao.cancelAppointment(appointmentId);
    }

    @Override
    public void rescheduleAppointment(int appointmentId, int doctorId,
                                      LocalDate date, String time) {
        dao.rescheduleAppointment(appointmentId, doctorId, date, time);
    }

    @Override
    public List<String> viewDailySchedule(LocalDate date) {
        return dao.getDailySchedule(date);
    }
}