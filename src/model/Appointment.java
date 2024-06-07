package model;

import java.util.Date;

public class Appointment {
    private int id;
    private String clientName;
    private Date appointmentDate;
    private String description;

    public Appointment(int id, String clientName, Date appointmentDate, String description) {
        this.id = id;
        this.clientName = clientName;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

