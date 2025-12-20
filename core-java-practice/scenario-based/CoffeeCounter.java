// package core-java-practice.scenario-based;

import java.util.Scanner;

public class CoffeeCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final double GST_RATE = 0.05; 

        while (true) {
            System.out.println("\nEnter coffee type (Espresso, Latte, Cappuccino) or type 'exit' to stop:");
            String coffeeType = sc.nextLine().toLowerCase();
            if (coffeeType.equals("exit")) {
                System.out.println("Cafe closed. Thank you!");
                break;
            }

            double pricePerCup;
            switch (coffeeType) {
                case "espresso":
                    pricePerCup = 110;
                    break;
                case "latte":
                    pricePerCup = 150;
                    break;
                case "cappuccino":
                    pricePerCup = 180;
                    break;
                default:
                    System.out.println("Invalid coffee type. Try again.");
                    continue; 
            }
            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine(); 
            double total = pricePerCup * quantity;
            double gst = total * GST_RATE;
            double finalBill = total + gst;
            System.out.println("\n--- Bill Details ---");
            System.out.println("Coffee Type : " + coffeeType);
            System.out.println("Quantity    : " + quantity);
            System.out.println("Base Amount : INR " + total);
            System.out.println("GST (5%)    : INR " + gst);
            System.out.println("Total Bill  : INR " + finalBill);
        }

        sc.close();
    }
}

        