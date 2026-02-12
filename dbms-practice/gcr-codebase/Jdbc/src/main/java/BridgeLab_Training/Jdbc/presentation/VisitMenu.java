package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.model.Prescription;
import BridgeLab_Training.Jdbc.service.VisitService;
import BridgeLab_Training.Jdbc.service.VisitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VisitMenu {

    private static final VisitService service =
            new VisitServiceImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n===== Visit Management =====");
            System.out.println("1. Record Patient Visit");
            System.out.println("2. View Patient Medical History");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> recordVisit(sc);
                    case 2 -> viewHistory(sc);
                    case 3 -> run = false;
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
//        sc.close();
    }

    private static void recordVisit(Scanner sc) {
        System.out.print("Appointment ID: ");
        int apptId = sc.nextInt();
        sc.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = sc.nextLine();

        System.out.print("Notes: ");
        String notes = sc.nextLine();

        List<Prescription> prescriptions = new ArrayList<>();

        while (true) {
            System.out.print("Medicine name (or 'done'): ");
            String med = sc.nextLine();
            if (med.equalsIgnoreCase("done")) break;

            System.out.print("Dosage: ");
            String dose = sc.nextLine();

            System.out.print("Duration: ");
            String dur = sc.nextLine();

            prescriptions.add(
                new Prescription(0, med, dose, dur)
            );
        }

        int visitId = service.completeVisit(
                apptId, diagnosis, notes, prescriptions
        );

        System.out.println("Visit recorded with ID: " + visitId);
    }

    private static void viewHistory(Scanner sc) {
        System.out.print("Patient ID: ");
        int pid = sc.nextInt();
        sc.nextLine();

        service.getPatientHistory(pid)
               .forEach(System.out::println);
    }
}