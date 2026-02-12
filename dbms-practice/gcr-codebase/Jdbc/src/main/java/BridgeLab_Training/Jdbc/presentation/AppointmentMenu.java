package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.service.AppointmentService;
import BridgeLab_Training.Jdbc.service.AppointmentServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class AppointmentMenu {

    private static final AppointmentService service =
            new AppointmentServiceImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n===== Appointment Scheduling =====");
            System.out.println("1. Book Appointment");
            System.out.println("2. Check Doctor Availability");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Reschedule Appointment");
            System.out.println("5. View Daily Schedule");
            System.out.println("6. Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {
                    case 1 -> {
                        System.out.print("Patient ID: ");
                        int pid = sc.nextInt();
                        System.out.print("Doctor ID: ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Date (yyyy-mm-dd): ");
                        LocalDate d = LocalDate.parse(sc.nextLine());
                        System.out.print("Time (HH:mm): ");
                        String t = sc.nextLine();

                        int id = service.bookAppointment(pid, did, d, t);
                        System.out.println("Appointment booked with ID: " + id);
                    }
                    case 2 -> {
                        System.out.print("Doctor ID: ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Date: ");
                        LocalDate d = LocalDate.parse(sc.nextLine());

                        service.checkAvailability(did, d)
                                .forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.print("Appointment ID: ");
                        service.cancelAppointment(sc.nextInt());
                        System.out.println("Appointment cancelled");
                    }
                    case 4 -> {
                        System.out.print("Appointment ID: ");
                        int aid = sc.nextInt();
                        System.out.print("Doctor ID: ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Date: ");
                        LocalDate d = LocalDate.parse(sc.nextLine());
                        System.out.print("Time: ");
                        String t = sc.nextLine();

                        service.rescheduleAppointment(aid, did, d, t);
                        System.out.println("Appointment rescheduled");
                    }
                    case 5 -> {
                        System.out.print("Date: ");
                        LocalDate d = LocalDate.parse(sc.nextLine());
                        service.viewDailySchedule(d)
                                .forEach(System.out::println);
                    }
                    case 6 -> run = false;
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
//        sc.close();
    }
}