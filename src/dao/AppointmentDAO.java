package dao;

import model.Appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentDAO {
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public Optional<Appointment> getAppointmentById(int id) {
        return appointments.stream().filter(a -> a.getId() == id).findFirst();
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public boolean updateAppointment(Appointment appointment) {
        Optional<Appointment> existingAppointment = getAppointmentById(appointment.getId());
        if (existingAppointment.isPresent()) {
            appointments.remove(existingAppointment.get());
            appointments.add(appointment);
            return true;
        }
        return false;
    }

    public boolean deleteAppointment(int id) {
        Optional<Appointment> existingAppointment = getAppointmentById(id);
        if (existingAppointment.isPresent()) {
            appointments.remove(existingAppointment.get());
            return true;
        }
        return false;
    }
}
