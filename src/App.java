import model.Appointment;
import model.User;
import service.AppointmentService;
import service.UserService;
import service.NotificationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private static AppointmentService appointmentService = new AppointmentService();
    private static UserService userService = new UserService();
    private static NotificationService notificationService = new NotificationService(appointmentService);
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy hh:mma");

    public static void main(String[] args) throws ParseException {
        // Register a default user for demonstration purposes
        userService.register(new User("admin", "password"));
        userService.register(new User("melisa", "12345")); // Add this line

        if (!login()) {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        notificationService.start();

        while (true) {
            System.out.println("1. Add Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addAppointment();
                    break;
                case 2:
                    viewAllAppointments();
                    break;
                case 3:
                    updateAppointment();
                    break;
                case 4:
                    deleteAppointment();
                    break;
                case 5:
                    notificationService.stop();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static boolean login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Optional<User> user = userService.login(username, password);
        return user.isPresent();
    }

    private static void addAppointment() throws ParseException {
        System.out.print("Enter Client Name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter Appointment Date (dd/MM/yyyy HH:mm or dd/MM/yyyy hh:mma): ");
        Date appointmentDate = parseDate(scanner.nextLine());
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        Appointment appointment = new Appointment(0, clientName, appointmentDate, description);
        if (appointmentService.addAppointment(appointment)) {
            System.out.println("Appointment added successfully.");
        } else {
            System.out.println("Failed to add appointment. Conflict with existing appointment.");
        }
    }

    private static Date parseDate(String dateString) throws ParseException {
        try {
            return dateFormat1.parse(dateString);
        } catch (ParseException e) {
            return dateFormat2.parse(dateString);
        }
    }

    private static void viewAllAppointments() {
        appointmentService.getAllAppointments().forEach(appointment -> {
            System.out.println("ID: " + appointment.getId() +
                    ", Client: " + appointment.getClientName() +
                    ", Date: " + dateFormat1.format(appointment.getAppointmentDate()) +
                    ", Description: " + appointment.getDescription());
        });
    }

    private static void updateAppointment() throws ParseException {
        System.out.print("Enter Appointment ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter New Client Name: ");
        String clientName = scanner.nextLine();
        System.out.print("Enter New Appointment Date (dd/MM/yyyy HH:mm or dd/MM/yyyy hh:mma): ");
        Date appointmentDate = parseDate(scanner.nextLine());
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();

        Appointment appointment = new Appointment(id, clientName, appointmentDate, description);
        if (appointmentService.updateAppointment(appointment)) {
            System.out.println("Appointment updated successfully.");
        } else {
            System.out.println("Failed to update appointment. Conflict with existing appointment.");
        }
    }

    private static void deleteAppointment() {
        System.out.print("Enter Appointment ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (appointmentService.deleteAppointment(id)) {
            System.out.println("Appointment deleted successfully.");
        } else {
            System.out.println("Failed to delete appointment. Appointment not found.");
        }
    }
}
