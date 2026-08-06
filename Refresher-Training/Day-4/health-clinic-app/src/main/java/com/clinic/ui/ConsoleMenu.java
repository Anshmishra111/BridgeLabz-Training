package com.clinic.ui;

import com.clinic.config.HikariConnectionPool;
import com.clinic.dao.*;
import com.clinic.dto.*;
import com.clinic.service.AppointmentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class ConsoleMenu {

    private final Scanner            scanner        = new Scanner(System.in);
    private final PatientDAO         patientDAO     = new PatientDAOImpl();
    private final DoctorDAO          doctorDAO      = new DoctorDAOImpl();
    private final SpecializationDAO  specDAO        = new SpecializationDAOImpl();
    private final AppointmentDAO     appointmentDAO = new AppointmentDAOImpl();
    private final BillingDAO         billingDAO     = new BillingDAOImpl();
    private final VisitHistoryDAO    visitDAO       = new VisitHistoryDAOImpl();
    private final AppointmentService appointmentSvc = new AppointmentService();

    // ═══════════════════════════════════════════════════════
    //  ENTRY POINT
    // ═══════════════════════════════════════════════════════

    public void start() {
        printBanner();
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice");

            switch (choice) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> specializationMenu();
                case 4 -> appointmentMenu();
                case 5 -> completeAppointmentFlow();
                case 6 -> billingMenu();
                case 7 -> visitHistoryMenu();
                case 8 -> reportsMenu();
                case 0 -> {
                    print("\n  Shutting down...");
                    HikariConnectionPool.close();
                    print("  Goodbye!\n");
                    running = false;
                }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    //  1. PATIENT MANAGEMENT
    // ═══════════════════════════════════════════════════════

    private void patientMenu() {
        while (true) {
            printSubMenu("PATIENT MANAGEMENT",
                "1. Register New Patient",
                "2. View Patient by ID",
                "3. View All Patients",
                "4. Search Patient by Name",
                "5. Update Patient",
                "6. Deactivate Patient (Soft Delete)",
                "7. Delete Patient (Hard Delete)",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> registerPatient();
                case 2 -> viewPatientById();
                case 3 -> viewAllPatients();
                case 4 -> searchPatient();
                case 5 -> updatePatient();
                case 6 -> deactivatePatient();
                case 7 -> deletePatient();
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    private void registerPatient() {
        printSection("Register New Patient");
        String firstName = readString("First name");
        String lastName  = readString("Last name");
        String dobStr    = readStringOptional("Date of birth (YYYY-MM-DD) [optional]");
        String gender    = readStringOptional("Gender (Male/Female/Other) [optional]");
        String phone     = readStringOptional("Phone number [optional]");
        String email     = readStringOptional("Email [optional]");

        Patient p = new Patient();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        if (!dobStr.isBlank()) {
            try { p.setDateOfBirth(LocalDate.parse(dobStr)); }
            catch (DateTimeParseException e) { print("  ⚠ Invalid date, skipping."); }
        }
        if (!gender.isBlank()) p.setGender(gender);
        if (!phone.isBlank())  p.setPhoneNumber(phone);
        if (!email.isBlank())  p.setEmail(email);

        int id = patientDAO.insertPatient(p);
        if (id > 0) printSuccess("Patient registered! Patient ID: " + id);
        else        printError("Registration failed. Phone or email may already exist.");
    }

    private void viewPatientById() {
        int id = readInt("Enter Patient ID");
        Patient p = patientDAO.getPatientById(id);
        if (p != null) { printDivider(); System.out.println("  " + p); printDivider(); }
        else printError("No patient found with ID: " + id);
    }

    private void viewAllPatients() {
        List<Patient> list = patientDAO.getAllPatients();
        printSection("All Patients (" + list.size() + ")");
        if (list.isEmpty()) { print("  No patients registered."); return; }
        list.forEach(p -> System.out.println("  " + p));
        printDivider();
    }

    private void searchPatient() {
        String name = readString("Enter name to search");
        List<Patient> results = patientDAO.searchPatientByName(name);
        if (results.isEmpty()) print("  No patients matching: " + name);
        else {
            printSection("Search Results (" + results.size() + ")");
            results.forEach(p -> System.out.println("  " + p));
        }
    }

    private void updatePatient() {
        int id = readInt("Enter Patient ID");
        Patient p = patientDAO.getPatientById(id);
        if (p == null) { printError("Patient not found."); return; }

        print("  Current: " + p);
        printSection("Update (press Enter to keep current value)");

        String fn    = readStringOptional("First name [" + p.getFirstName() + "]");
        String ln    = readStringOptional("Last name  [" + p.getLastName() + "]");
        String ph    = readStringOptional("Phone      [" + nvl(p.getPhoneNumber()) + "]");
        String em    = readStringOptional("Email      [" + nvl(p.getEmail()) + "]");
        String gen   = readStringOptional("Gender     [" + nvl(p.getGender()) + "]");

        if (!fn.isBlank())  p.setFirstName(fn);
        if (!ln.isBlank())  p.setLastName(ln);
        if (!ph.isBlank())  p.setPhoneNumber(ph);
        if (!em.isBlank())  p.setEmail(em);
        if (!gen.isBlank()) p.setGender(gen);

        if (patientDAO.updatePatient(p)) printSuccess("Patient updated.");
        else                             printError("Update failed.");
    }

    private void deactivatePatient() {
        int id = readInt("Enter Patient ID to deactivate");
        Patient p = patientDAO.getPatientById(id);
        if (p == null) { printError("Not found."); return; }
        p.setActive(false);
        if (patientDAO.updatePatient(p)) printSuccess("Patient deactivated (is_active = false).");
        else                             printError("Deactivation failed.");
    }

    private void deletePatient() {
        int id = readInt("Enter Patient ID to DELETE");
        Patient p = patientDAO.getPatientById(id);
        if (p == null) { printError("Not found."); return; }
        print("  About to delete: " + p);
        if (!readString("Type 'yes' to confirm").equalsIgnoreCase("yes")) {
            print("  Cancelled."); return;
        }
        if (patientDAO.deletePatient(id)) printSuccess("Patient deleted.");
        else                              printError("Delete failed. Patient has linked appointments.");
    }

    // ═══════════════════════════════════════════════════════
    //  2. DOCTOR MANAGEMENT
    // ═══════════════════════════════════════════════════════

    private void doctorMenu() {
        while (true) {
            printSubMenu("DOCTOR MANAGEMENT",
                "1. Register New Doctor",
                "2. View Doctor by ID",
                "3. View All Doctors",
                "4. Update Doctor",
                "5. Delete Doctor",
                "6. Assign Specialization",
                "7. Remove Specialization",
                "8. View Doctor Specializations",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> registerDoctor();
                case 2 -> viewDoctorById();
                case 3 -> viewAllDoctors();
                case 4 -> updateDoctor();
                case 5 -> deleteDoctor();
                case 6 -> assignSpecializationToDoctor();
                case 7 -> removeSpecializationFromDoctor();
                case 8 -> viewDoctorSpecializations();
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    private void registerDoctor() {
        printSection("Register New Doctor");
        String fn    = readString("First name");
        String ln    = readString("Last name");
        String phone = readStringOptional("Phone number [optional]");
        String email = readStringOptional("Email [optional]");

        Doctor d = new Doctor(fn, ln,
            phone.isBlank() ? null : phone,
            email.isBlank() ? null : email);
        int id = doctorDAO.insertDoctor(d);
        if (id > 0) printSuccess("Doctor registered! Doctor ID: " + id);
        else        printError("Failed. Phone or email may already exist.");
    }

    private void viewDoctorById() {
        int id = readInt("Enter Doctor ID");
        Doctor d = doctorDAO.getDoctorById(id);
        if (d != null) {
            printDivider();
            System.out.println("  " + d);
            List<Specialization> specs = doctorDAO.getDoctorSpecializations(id);
            if (!specs.isEmpty()) {
                print("  Specializations:");
                specs.forEach(s -> System.out.println("    → " + s.getName()));
            }
            printDivider();
        } else {
            printError("No doctor found with ID: " + id);
        }
    }

    private void viewAllDoctors() {
        List<Doctor> list = doctorDAO.getAllDoctors();
        printSection("All Doctors (" + list.size() + ")");
        if (list.isEmpty()) { print("  No doctors."); return; }
        list.forEach(d -> System.out.println("  " + d));
        printDivider();
    }

    private void updateDoctor() {
        int id = readInt("Enter Doctor ID");
        Doctor d = doctorDAO.getDoctorById(id);
        if (d == null) { printError("Not found."); return; }
        print("  Current: " + d);
        String fn = readStringOptional("First name [" + d.getFirstName() + "]");
        String ln = readStringOptional("Last name  [" + d.getLastName() + "]");
        String ph = readStringOptional("Phone      [" + nvl(d.getPhoneNumber()) + "]");
        String em = readStringOptional("Email      [" + nvl(d.getEmail()) + "]");
        if (!fn.isBlank()) d.setFirstName(fn);
        if (!ln.isBlank()) d.setLastName(ln);
        if (!ph.isBlank()) d.setPhoneNumber(ph);
        if (!em.isBlank()) d.setEmail(em);
        if (doctorDAO.updateDoctor(d)) printSuccess("Doctor updated.");
        else                           printError("Update failed.");
    }

    private void deleteDoctor() {
        int id = readInt("Enter Doctor ID to delete");
        Doctor d = doctorDAO.getDoctorById(id);
        if (d == null) { printError("Not found."); return; }
        print("  About to delete: " + d);
        if (!readString("Type 'yes' to confirm").equalsIgnoreCase("yes")) { print("  Cancelled."); return; }
        if (doctorDAO.deleteDoctor(id)) printSuccess("Doctor deleted.");
        else                            printError("Delete failed. Doctor has linked appointments.");
    }

    private void assignSpecializationToDoctor() {
        int doctorId = readInt("Enter Doctor ID");
        List<Specialization> all = specDAO.getAllSpecializations();
        if (all.isEmpty()) { printError("No specializations found. Add some first."); return; }
        printSection("Available Specializations");
        all.forEach(s -> System.out.printf("  [%3d] %s%n", s.getSpecializationId(), s.getName()));
        int specId = readInt("Enter Specialization ID to assign");
        if (doctorDAO.assignSpecialization(doctorId, specId)) printSuccess("Assigned.");
        else                                                   printError("Assignment failed.");
    }

    private void removeSpecializationFromDoctor() {
        int doctorId = readInt("Enter Doctor ID");
        List<Specialization> cur = doctorDAO.getDoctorSpecializations(doctorId);
        if (cur.isEmpty()) { print("  No specializations assigned."); return; }
        cur.forEach(s -> System.out.printf("  [%3d] %s%n", s.getSpecializationId(), s.getName()));
        int specId = readInt("Enter Specialization ID to remove");
        if (doctorDAO.removeSpecialization(doctorId, specId)) printSuccess("Removed.");
        else                                                   printError("Removal failed.");
    }

    private void viewDoctorSpecializations() {
        int doctorId = readInt("Enter Doctor ID");
        Doctor d = doctorDAO.getDoctorById(doctorId);
        if (d == null) { printError("Not found."); return; }
        List<Specialization> specs = doctorDAO.getDoctorSpecializations(doctorId);
        printSection("Specializations of Dr. " + d.getFirstName() + " " + d.getLastName());
        if (specs.isEmpty()) print("  None assigned.");
        else specs.forEach(s -> System.out.println("  → " + s));
    }

    // ═══════════════════════════════════════════════════════
    //  3. SPECIALIZATION MANAGEMENT
    // ═══════════════════════════════════════════════════════

    private void specializationMenu() {
        while (true) {
            printSubMenu("SPECIALIZATION MANAGEMENT",
                "1. View All",
                "2. Add New",
                "3. Update",
                "4. Delete",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> { specDAO.getAllSpecializations().forEach(s -> System.out.println("  " + s)); }
                case 2 -> {
                    String name = readString("Name");
                    String desc = readStringOptional("Description [optional]");
                    int id = specDAO.insertSpecialization(new Specialization(name, desc.isBlank() ? null : desc));
                    if (id > 0) printSuccess("Added with ID: " + id);
                    else        printError("Failed. Name may already exist.");
                }
                case 3 -> {
                    int id = readInt("Specialization ID");
                    Specialization s = specDAO.getSpecializationById(id);
                    if (s == null) { printError("Not found."); break; }
                    String nm = readStringOptional("Name [" + s.getName() + "]");
                    String dc = readStringOptional("Desc [" + nvl(s.getDescription()) + "]");
                    if (!nm.isBlank()) s.setName(nm);
                    if (!dc.isBlank()) s.setDescription(dc);
                    if (specDAO.updateSpecialization(s)) printSuccess("Updated.");
                    else                                 printError("Update failed.");
                }
                case 4 -> {
                    int id = readInt("Specialization ID to delete");
                    if (specDAO.deleteSpecialization(id)) printSuccess("Deleted.");
                    else                                  printError("Delete failed.");
                }
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    //  4. APPOINTMENT MANAGEMENT
    // ═══════════════════════════════════════════════════════

    private void appointmentMenu() {
        while (true) {
            printSubMenu("APPOINTMENT BOOKING",
                "1. Book New Appointment",
                "2. View Appointment by ID",
                "3. View All Appointments",
                "4. Appointments by Patient",
                "5. Appointments by Doctor",
                "6. Cancel Appointment",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> bookAppointment();
                case 2 -> viewAppointmentById();
                case 3 -> viewAllAppointments();
                case 4 -> { int pid = readInt("Patient ID"); appointmentDAO.getAppointmentsByPatient(pid).forEach(a -> System.out.println("  " + a)); }
                case 5 -> { int did = readInt("Doctor ID");  appointmentDAO.getAppointmentsByDoctor(did).forEach(a -> System.out.println("  " + a)); }
                case 6 -> cancelAppointment();
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    private void bookAppointment() {
        printSection("Book New Appointment");

        int patientId = readInt("Patient ID");
        if (patientDAO.getPatientById(patientId) == null) {
            printError("Patient ID " + patientId + " not found."); return;
        }

        int doctorId = readInt("Doctor ID");
        if (doctorDAO.getDoctorById(doctorId) == null) {
            printError("Doctor ID " + doctorId + " not found."); return;
        }

        // User's schema: appointment_date is DATETIME (date + time together)
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            String input = readString("Appointment date & time (YYYY-MM-DDTHH:MM, e.g. 2026-08-10T10:00)");
            try {
                dateTime = LocalDateTime.parse(input);
            } catch (DateTimeParseException e) {
                print("  ✗ Invalid format. Use YYYY-MM-DDTHH:MM (e.g. 2026-08-10T10:00)");
            }
        }

        Appointment a = new Appointment(patientId, doctorId, dateTime);
        int id = appointmentDAO.insertAppointment(a);
        if (id > 0) printSuccess("Appointment booked! Appointment ID: " + id);
        else        printError("Booking failed.");
    }

    private void viewAppointmentById() {
        int id = readInt("Appointment ID");
        Appointment a = appointmentDAO.getAppointmentById(id);
        if (a != null) { printDivider(); System.out.println("  " + a); printDivider(); }
        else printError("Not found.");
    }

    private void viewAllAppointments() {
        List<Appointment> list = appointmentDAO.getAllAppointments();
        printSection("All Appointments (" + list.size() + ")");
        if (list.isEmpty()) { print("  None."); return; }
        list.forEach(a -> System.out.println("  " + a));
        printDivider();
    }

    private void cancelAppointment() {
        int id = readInt("Appointment ID to cancel");
        Appointment a = appointmentDAO.getAppointmentById(id);
        if (a == null) { printError("Not found."); return; }
        if (!"Scheduled".equals(a.getStatus())) {
            printError("Cannot cancel — status is: " + a.getStatus()); return;
        }
        if (appointmentDAO.cancelAppointment(id)) printSuccess("Appointment cancelled.");
        else                                      printError("Cancellation failed.");
    }

    // ═══════════════════════════════════════════════════════
    //  5. COMPLETE APPOINTMENT — The 3-write ACID transaction
    // ═══════════════════════════════════════════════════════

    private void completeAppointmentFlow() {
        printSection("COMPLETE APPOINTMENT");
        print("  This will atomically:");
        print("    1. Mark appointment → Completed");
        print("    2. Create a Pending bill in billing table");
        print("    3. Record diagnosis + prescription in visit_history");
        print("  All 3 succeed together, or ALL are rolled back.\n");

        int id = readInt("Appointment ID");
        Appointment a = appointmentDAO.getAppointmentById(id);
        if (a == null) { printError("Appointment not found."); return; }
        if (!"Scheduled".equals(a.getStatus())) {
            printError("Cannot complete — status is: " + a.getStatus()); return;
        }
        print("  Appointment: " + a);

        // Read bill amount
        BigDecimal amount = null;
        while (amount == null) {
            String amtStr = readString("Bill amount (e.g. 750.00)");
            try {
                amount = new BigDecimal(amtStr);
                if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                    print("  ✗ Amount must be > 0."); amount = null;
                }
            } catch (NumberFormatException e) {
                print("  ✗ Enter a number like 750.00");
            }
        }

        String diagnosis    = readString("Diagnosis");
        String prescription = readStringOptional("Prescription [optional]");
        String visitNotes   = readStringOptional("Visit notes [optional]");  // maps to visit_notes

        print("\n  Processing transaction...");
        boolean success = appointmentSvc.completeAppointment(
            id, amount, diagnosis,
            prescription.isBlank() ? null : prescription,
            visitNotes.isBlank()   ? null : visitNotes
        );

        if (success) {
            printSuccess("✔ Appointment completed! Bill and visit record created.");
            Billing bill = billingDAO.getBillingByAppointment(id);
            if (bill != null) System.out.println("  " + bill);
        } else {
            printError("✗ Transaction failed. Nothing was saved. Check logs above.");
        }
    }

    // ═══════════════════════════════════════════════════════
    //  6. BILLING & PAYMENT
    // ═══════════════════════════════════════════════════════

    private void billingMenu() {
        while (true) {
            printSubMenu("BILLING & PAYMENT",
                "1. View All Bills",
                "2. View Bill by Appointment ID",
                "3. Mark as PAID",
                "4. Mark as REFUNDED",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> {
                    List<Billing> list = billingDAO.getAllBillings();
                    printSection("All Bills (" + list.size() + ")");
                    list.forEach(b -> System.out.println("  " + b));
                }
                case 2 -> {
                    int apptId = readInt("Appointment ID");
                    Billing b = billingDAO.getBillingByAppointment(apptId);
                    if (b != null) { printDivider(); System.out.println("  " + b); printDivider(); }
                    else printError("No bill for Appointment ID: " + apptId);
                }
                case 3 -> {
                    int billId = readInt("Bill ID (bill_id)");
                    if (billingDAO.updatePaymentStatus(billId, "Paid"))
                        printSuccess("Marked as Paid.");
                    else printError("Update failed. Bill ID not found.");
                }
                case 4 -> {
                    int billId = readInt("Bill ID (bill_id)");
                    if (billingDAO.updatePaymentStatus(billId, "Refunded"))
                        printSuccess("Marked as Refunded.");
                    else printError("Update failed. Bill ID not found.");
                }
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    //  7. VISIT HISTORY
    // ═══════════════════════════════════════════════════════

    private void visitHistoryMenu() {
        while (true) {
            printSubMenu("VISIT HISTORY",
                "1. All Visit Records",
                "2. By Appointment ID",
                "3. Patient's Full History",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> visitDAO.getAllVisitHistory().forEach(v -> System.out.println("  " + v));
                case 2 -> {
                    int apptId = readInt("Appointment ID");
                    VisitHistory v = visitDAO.getVisitByAppointment(apptId);
                    if (v != null) { printDivider(); System.out.println("  " + v); printDivider(); }
                    else printError("No visit for Appointment ID: " + apptId);
                }
                case 3 -> {
                    int pid = readInt("Patient ID");
                    Patient p = patientDAO.getPatientById(pid);
                    if (p == null) { printError("Patient not found."); break; }
                    List<VisitHistory> visits = visitDAO.getVisitsByPatient(pid);
                    printSection("Visit History — " + p.getFirstName() + " " + p.getLastName()
                                 + " (" + visits.size() + " visits)");
                    if (visits.isEmpty()) print("  No visits yet.");
                    else visits.forEach(v -> System.out.println("  " + v));
                }
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    // ═══════════════════════════════════════════════════════
    //  8. REPORTS
    // ═══════════════════════════════════════════════════════

    private void reportsMenu() {
        while (true) {
            printSubMenu("REPORTS",
                "1. All Scheduled Appointments",
                "2. All Completed Appointments",
                "3. All Cancelled Appointments",
                "4. Pending Payments",
                "5. Patient Summary",
                "0. Back");

            switch (readInt("Enter choice")) {
                case 1 -> filterAppointments("Scheduled");
                case 2 -> filterAppointments("Completed");
                case 3 -> filterAppointments("Cancelled");
                case 4 -> {
                    printSection("Pending Payments");
                    billingDAO.getAllBillings().stream()
                        .filter(b -> "Pending".equals(b.getPaymentStatus()))
                        .forEach(b -> System.out.println("  " + b));
                }
                case 5 -> patientSummaryReport();
                case 0 -> { return; }
                default -> print("  ✗ Invalid choice.");
            }
        }
    }

    private void filterAppointments(String status) {
        printSection(status + " Appointments");
        long count = appointmentDAO.getAllAppointments().stream()
            .filter(a -> status.equals(a.getStatus()))
            .peek(a -> System.out.println("  " + a))
            .count();
        if (count == 0) print("  None found.");
    }

    private void patientSummaryReport() {
        int pid = readInt("Patient ID");
        Patient p = patientDAO.getPatientById(pid);
        if (p == null) { printError("Not found."); return; }
        printSection("Summary — " + p.getFirstName() + " " + p.getLastName());
        System.out.println("  " + p);
        List<Appointment> appts = appointmentDAO.getAppointmentsByPatient(pid);
        print("\n  Total Appointments: " + appts.size());
        appts.forEach(a -> System.out.println("    " + a));
        List<VisitHistory> visits = visitDAO.getVisitsByPatient(pid);
        print("\n  Total Visits: " + visits.size());
        visits.forEach(v -> System.out.println("    " + v));
    }

    // ═══════════════════════════════════════════════════════
    //  UI Utility — printing + input
    // ═══════════════════════════════════════════════════════

    private void printBanner() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║        HEALTH CLINIC MANAGEMENT SYSTEM          ║");
        System.out.println("  ║           Powered by JDBC + HikariCP            ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void printMainMenu() {
        System.out.println("\n  ┌─────────────────────────────────────────────────┐");
        System.out.println("  │                   MAIN MENU                     │");
        System.out.println("  ├─────────────────────────────────────────────────┤");
        System.out.println("  │  1. Patient Management                          │");
        System.out.println("  │  2. Doctor Management                           │");
        System.out.println("  │  3. Specialization Management                   │");
        System.out.println("  │  4. Appointment Booking                         │");
        System.out.println("  │  5. ★ Complete Appointment (Bill + Visit)       │");
        System.out.println("  │  6. Billing & Payment                           │");
        System.out.println("  │  7. Visit History                               │");
        System.out.println("  │  8. Reports                                     │");
        System.out.println("  │  0. Exit                                        │");
        System.out.println("  └─────────────────────────────────────────────────┘");
    }

    private void printSubMenu(String title, String... options) {
        System.out.println("\n  ─── " + title + " ───");
        for (String opt : options) System.out.println("  " + opt);
    }

    private void printSection(String title) {
        System.out.println("\n  ──────────────────────────────────────────────────");
        System.out.println("  " + title);
        System.out.println("  ──────────────────────────────────────────────────");
    }

    private void printDivider() { System.out.println("  ──────────────────────────────────────────────────"); }
    private void printSuccess(String msg) { System.out.println("\n  ✔ " + msg); }
    private void printError(String msg)   { System.out.println("\n  ✗ ERROR: " + msg); }
    private void print(String msg)        { System.out.println(msg); }
    private String nvl(String s)          { return s != null ? s : ""; }

    private String readString(String prompt) {
        while (true) {
            System.out.print("  " + prompt + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            print("  ✗ This field is required.");
        }
    }

    private String readStringOptional(String prompt) {
        System.out.print("  " + prompt + ": ");
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print("  " + prompt + ": ");
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { print("  ✗ Enter a valid number."); }
        }
    }
}
