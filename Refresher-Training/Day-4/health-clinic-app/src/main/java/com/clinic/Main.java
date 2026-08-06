package com.clinic;

import com.clinic.ui.ConsoleMenu;


public class Main {

    public static void main(String[] args) {
        System.out.println("\n  Starting Health Clinic Management System...");
        System.out.println("  Connecting to database...\n");

        new ConsoleMenu().start();
    }
}
