package com.clinic;

import com.clinic.ui.ConsoleMenu;

/**
 * Main — The entry point.
 *
 * The entire application is one line:
 *   new ConsoleMenu().start()
 *
 * This is intentional. Main's only job is to start the UI.
 * All logic, all database access, all transaction management
 * lives in the layers below.
 *
 * To run: mvn compile exec:java -Dexec.mainClass=com.clinic.Main
 * Or:     mvn package → java -jar target/HealthClinicApp-1.0-SNAPSHOT.jar
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("\n  Starting Health Clinic Management System...");
        System.out.println("  Connecting to database...\n");

        new ConsoleMenu().start();
    }
}
