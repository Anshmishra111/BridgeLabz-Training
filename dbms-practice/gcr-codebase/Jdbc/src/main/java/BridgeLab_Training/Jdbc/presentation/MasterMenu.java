package BridgeLab_Training.Jdbc.presentation;

import java.util.Scanner;

public class MasterMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====================================");
            System.out.println("  HEALTH CLINIC MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Scheduling");
            System.out.println("4. Visit & Medical Records");
            System.out.println("5. Billing & Payments");
            System.out.println("6. Administration");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> PatientMenu.main(null);
                case 2 -> DoctorMenu.main(null);
                case 3 -> AppointmentMenu.main(null);
                case 4 -> VisitMenu.main(null);
                case 5 -> BillingMenu.main(null);
                case 6 -> AdminMenu.main(null);
                case 7 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("❌ Invalid choice");
            }
        }
        sc.close();
    }
}