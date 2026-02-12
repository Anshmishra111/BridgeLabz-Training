package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.model.Speciality;
import BridgeLab_Training.Jdbc.service.SpecialityService;
import BridgeLab_Training.Jdbc.service.SpecialityServiceImpl;
import BridgeLab_Training.Jdbc.dao.AuditLogDAO;
import BridgeLab_Training.Jdbc.dao.AuditLogDAOImpl;
import BridgeLab_Training.Jdbc.util.DatabaseBackupUtil;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    private static final SpecialityService specialtyService =
            new SpecialityServiceImpl();

    private static final AuditLogDAO auditLogDAO =
            new AuditLogDAOImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n========== ADMINISTRATION ==========");
            System.out.println("1. Manage Specialties");
            System.out.println("2. View Audit Logs");
            System.out.println("3. Validate Database Backup");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> manageSpecialties(sc);
                    case 2 -> viewAuditLogs(sc);
                    case 3 -> runBackupValidation();
                    case 4 -> {
                        running = false;
                        System.out.println("Exiting Admin Menu...");
                    }
                    default -> System.out.println("❌ Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
//        sc.close();
    }

    // ================= UC-6.1 =================
    private static void manageSpecialties(Scanner sc) {

        boolean back = false;

        while (!back) {
            System.out.println("\n--- Specialty Management ---");
            System.out.println("1. Add Specialty");
            System.out.println("2. View All Specialties");
            System.out.println("3. Update Specialty");
            System.out.println("4. Delete Specialty");
            System.out.println("5. Back");
            System.out.print("Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter specialty name: ");
                    String name = sc.nextLine();
                    int id = specialtyService.addSpecialty(name);
                    System.out.println("✅ Specialty added with ID: " + id);
                }
                case 2 -> {
                    List<Speciality> list = specialtyService.listSpecialties();
                    if (list.isEmpty()) {
                        System.out.println("No specialties found.");
                    } else {
                        System.out.println("\nID | Specialty");
                        for (Speciality s : list) {
                            System.out.println(
                                s.getSpecialtyId() + " | " +
                                s.getSpecialtyName()
                            );
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Enter specialty ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();
                    specialtyService.updateSpecialty(id, newName);
                    System.out.println("✅ Specialty updated");
                }
                case 4 -> {
                    System.out.print("Enter specialty ID to delete: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    specialtyService.deleteSpecialty(id);
                    System.out.println("✅ Specialty deleted");
                }
                case 5 -> back = true;
                default -> System.out.println("❌ Invalid choice");
            }
        }
    }

    // ================= UC-6.3 =================
    private static void viewAuditLogs(Scanner sc) {

        System.out.print("Enter table name (or 'all'): ");
        String table = sc.nextLine();

        System.out.print("Enter user (or 'all'): ");
        String user = sc.nextLine();

        List<String> logs =
                auditLogDAO.getAuditLogs(
                        table.equalsIgnoreCase("all") ? "" : table,
                        user.equalsIgnoreCase("all") ? "" : user
                );

        if (logs.isEmpty()) {
            System.out.println("No audit logs found.");
        } else {
            System.out.println("\n--- Audit Logs ---");
            logs.forEach(System.out::println);
        }
    }

    // ================= UC-6.2 =================
    private static void runBackupValidation() {
        System.out.println("\nValidating database schema for backup...");
        DatabaseBackupUtil.validateSchema();
        System.out.println("✅ Database schema validation complete");
    }
}