package BridgeLab_Training.Jdbc.presentation;

import BridgeLab_Training.Jdbc.service.BillingService;
import BridgeLab_Training.Jdbc.service.BillingServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class BillingMenu {

    private static final BillingService service =
            new BillingServiceImpl();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\n===== Billing & Payments =====");
            System.out.println("1. Generate Bill");
            System.out.println("2. Record Payment");
            System.out.println("3. View Outstanding Bills");
            System.out.println("4. Revenue Report");
            System.out.println("5. Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {
                    case 1 -> {
                        System.out.print("Visit ID: ");
                        int visitId = sc.nextInt();
                        System.out.print("Additional charges: ");
                        double add = sc.nextDouble();
                        int billId = service.generateBill(visitId, add);
                        System.out.println("Bill generated with ID: " + billId);
                    }
                    case 2 -> {
                        System.out.print("Bill ID: ");
                        int bid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Payment Mode: ");
                        String mode = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        service.recordPayment(bid, mode, amt);
                        System.out.println("Payment recorded");
                    }
                    case 3 ->
                        service.viewOutstandingBills()
                               .forEach(System.out::println);
                    case 4 -> {
                        System.out.print("From date: ");
                        LocalDate from = LocalDate.parse(sc.nextLine());
                        System.out.print("To date: ");
                        LocalDate to = LocalDate.parse(sc.nextLine());
                        service.revenueReport(from, to)
                               .forEach(System.out::println);
                    }
                    case 5 -> run = false;
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
//        sc.close();
    }
}