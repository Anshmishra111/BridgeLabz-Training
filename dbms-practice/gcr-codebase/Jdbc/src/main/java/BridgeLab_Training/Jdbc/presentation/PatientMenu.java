package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.model.Patient;
import BridgeLab_Training.Jdbc.service.PatientService;
import BridgeLab_Training.Jdbc.service.PatientServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PatientMenu {

    private static final PatientService patientService =
            new PatientServiceImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Patient Management =====");
            System.out.println("1. Register New Patient");
            System.out.println("2. Update Patient Information");
            System.out.println("3. Search Patient by Name");
            System.out.println("4. Search Patient by Phone");
            System.out.println("5. View Patient Visit History");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> registerPatient(sc);
                    case 2 -> updatePatient(sc);
                    case 3 -> searchByName(sc);
                    case 4 -> searchByPhone(sc);
                    case 5 -> viewVisitHistory(sc);
                    case 6 -> {
                        running = false;
                        System.out.println("Exiting Patient Module...");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
//        sc.close();
    }

    // UC-1.1
    private static void registerPatient(Scanner sc) {

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter DOB (yyyy-mm-dd): ");
        LocalDate dob = LocalDate.parse(sc.nextLine());

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter blood group: ");
        String bloodGroup = sc.nextLine();

        Patient patient = new Patient(
                name, dob, phone, email, address, bloodGroup
        );

        int id = patientService.registerPatient(patient);
        System.out.println("✅ Patient registered successfully!");
        System.out.println("Generated Patient ID: " + id);
    }

    // UC-1.2
    private static void updatePatient(Scanner sc) {

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();

        Patient patient = patientService.getPatientById(patientId);

        System.out.println("Current Name: " + patient.getName());
        System.out.print("New Name (Enter to keep same): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) patient.setName(name);

        System.out.println("Current Address: " + patient.getAddress());
        System.out.print("New Address (Enter to keep same): ");
        String address = sc.nextLine();
        if (!address.isEmpty()) patient.setAddress(address);

        System.out.println("Current Blood Group: " + patient.getBloodGroup());
        System.out.print("New Blood Group (Enter to keep same): ");
        String bloodGroup = sc.nextLine();
        if (!bloodGroup.isEmpty()) patient.setBloodGroup(bloodGroup);

        boolean updated = patientService.updatePatient(patient);

        if (updated) {
            System.out.println("✅ Patient updated successfully!");
        } else {
            System.out.println("❌ Update failed!");
        }
    }

    // UC-1.3 (LIKE search)
    private static void searchByName(Scanner sc) {

        System.out.print("Enter name keyword: ");
        String keyword = sc.nextLine();

        List<Patient> patients =
                patientService.searchPatientsByName(keyword);

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\nID | Name | Phone | Blood Group");
        for (Patient p : patients) {
            System.out.println(
                    p.getPatientId() + " | " +
                    p.getName() + " | " +
                    p.getPhone() + " | " +
                    p.getBloodGroup()
            );
        }
    }

    // UC-1.3 (Exact search)
    private static void searchByPhone(Scanner sc) {

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        Patient p = patientService.getPatientByPhone(phone);

        System.out.println("\nPatient Details:");
        System.out.println("ID: " + p.getPatientId());
        System.out.println("Name: " + p.getName());
        System.out.println("DOB: " + p.getDob());
        System.out.println("Email: " + p.getEmail());
        System.out.println("Address: " + p.getAddress());
        System.out.println("Blood Group: " + p.getBloodGroup());
    }

    // UC-1.4
    private static void viewVisitHistory(Scanner sc) {

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();
        sc.nextLine();

        List<String> history =
                patientService.getVisitHistory(patientId);

        if (history.isEmpty()) {
            System.out.println("No visit history found.");
            return;
        }

        System.out.println("\nVisit History:");
        for (String record : history) {
            System.out.println(record);
        }
    }
}