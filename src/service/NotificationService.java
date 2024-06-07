package service;

import model.Appointment;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService {
    private AppointmentService appointmentService;
    private Timer timer;

    public NotificationService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new NotificationTask(), 0, 60000); // Check every minute
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private class NotificationTask extends TimerTask {
        @Override
        public void run() {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            Date now = new Date();
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentDate().after(now) &&
                        appointment.getAppointmentDate().getTime() - now.getTime() <= 3600000) { // within 1 hour
                    System.out.println("Reminder: Appointment with " + appointment.getClientName() +
                            " at " + appointment.getAppointmentDate() + " is coming up in less than an hour.");
                }
            }
        }
    }
}

