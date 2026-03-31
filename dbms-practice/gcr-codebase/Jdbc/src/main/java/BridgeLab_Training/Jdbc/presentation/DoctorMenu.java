package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.model.Doctor;
import BridgeLab_Training.Jdbc.service.DoctorService;
import BridgeLab_Training.Jdbc.service.DoctorServiceImpl;

import java.util.List;
import java.util.Scanner;

public class DoctorMenu {

    private static final DoctorService doctorService =
            new DoctorServiceImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n===== Doctor Management =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor Specialty");
            System.out.println("3. View Doctors by Specialty");
            System.out.println("4. Deactivate Doctor");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> addDoctor(sc);
                    case 2 -> updateSpecialty(sc);
                    case 3 -> viewBySpecialty(sc);
                    case 4 -> deactivateDoctor(sc);
                    case 5 -> run = false;
                    default -> System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
//        sc.close();
    }

    private static void addDoctor(Scanner sc) {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Contact: ");
        String contact = sc.nextLine();

        System.out.print("Consultation Fee: ");
        double fee = sc.nextDouble();

        System.out.print("Specialty ID: ");
        int sid = sc.nextInt();
        sc.nextLine();

        Doctor d = new Doctor(name, contact, fee, sid);
        int id = doctorService.addDoctor(d);
        System.out.println("Doctor added with ID: " + id);
    }

    private static void updateSpecialty(Scanner sc) {
        System.out.print("Doctor ID: ");
        int did = sc.nextInt();

        System.out.print("New Specialty ID: ");
        int sid = sc.nextInt();
        sc.nextLine();

        doctorService.updateDoctorSpecialty(did, sid);
        System.out.println("Specialty updated");
    }

    private static void viewBySpecialty(Scanner sc) {
        System.out.print("Specialty name: ");
        String name = sc.nextLine();

        List<Doctor> doctors = doctorService.getDoctorsBySpecialty(name);

        for (Doctor d : doctors) {
            System.out.println(
                d.getDoctorId() + " | " +
                d.getName() + " | " +
                d.getConsultationFee()
            );
        }
    }

    private static void deactivateDoctor(Scanner sc) {
        System.out.print("Doctor ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        doctorService.deactivateDoctor(id);
        System.out.println("Doctor deactivated");
    }
}