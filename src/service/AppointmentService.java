package service;

import model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private List<Appointment> appointments = new ArrayList<>();
    private static int idCounter = 1; // Initialize the counter

    public boolean addAppointment(Appointment appointment) {
        // Assign a unique ID
        appointment.setId(idCounter++);
        if (isConflict(appointment)) {
            return false;
        }
        appointments.add(appointment);
        return true;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public boolean updateAppointment(Appointment appointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getId() == appointment.getId()) {
                if (isConflict(appointment)) {
                    return false;
                }
                appointments.set(i, appointment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAppointment(int id) {
        return appointments.removeIf(appointment -> appointment.getId() == id);
    }

    private boolean isConflict(Appointment newAppointment) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() != newAppointment.getId() &&
                    appointment.getAppointmentDate().equals(newAppointment.getAppointmentDate())) {
                return true;
            }
        }
        return false;
    }
}
