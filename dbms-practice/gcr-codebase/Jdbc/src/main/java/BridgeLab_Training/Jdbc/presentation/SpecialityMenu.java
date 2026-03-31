package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.model.Speciality;
import BridgeLab_Training.Jdbc.service.SpecialityService;
import BridgeLab_Training.Jdbc.service.SpecialityServiceImpl;

import java.util.List;
import java.util.Scanner;

public class SpecialityMenu {

    private static final SpecialityService specialtyService =
            new SpecialityServiceImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== Specialty Management =====");
            System.out.println("1. Add Specialty");
            System.out.println("2. View All Specialties");
            System.out.println("3. Update Specialty");
            System.out.println("4. Delete Specialty");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> addSpecialty(sc);
                    case 2 -> viewSpecialties();
                    case 3 -> updateSpecialty(sc);
                    case 4 -> deleteSpecialty(sc);
                    case 5 -> {
                        running = false;
                        System.out.println("Exiting Specialty Management...");
                    }
                    default -> System.out.println("❌ Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
//        sc.close();
    }

    // UC-6.1 → Add Specialty
    private static void addSpecialty(Scanner sc) {
        System.out.print("Enter specialty name: ");
        String name = sc.nextLine();

        int id = specialtyService.addSpecialty(name);
        System.out.println("✅ Specialty added with ID: " + id);
    }

    // UC-6.1 → View Specialties
    private static void viewSpecialties() {
        List<Speciality> specialties = specialtyService.listSpecialties();

        if (specialties.isEmpty()) {
            System.out.println("No specialties found.");
            return;
        }

        System.out.println("\nID | Specialty Name");
        System.out.println("--------------------");
        for (Speciality s : specialties) {
            System.out.println(
                s.getSpecialtyId() + " | " + s.getSpecialtyName()
            );
        }
    }

    // UC-6.1 → Update Specialty
    private static void updateSpecialty(Scanner sc) {
        System.out.print("Enter specialty ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new specialty name: ");
        String newName = sc.nextLine();

        specialtyService.updateSpecialty(id, newName);
        System.out.println("✅ Specialty updated successfully");
    }

    // UC-6.1 → Delete Specialty (FK safe)
    private static void deleteSpecialty(Scanner sc) {
        System.out.print("Enter specialty ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        specialtyService.deleteSpecialty(id);
        System.out.println("✅ Specialty deleted successfully");
    }
}