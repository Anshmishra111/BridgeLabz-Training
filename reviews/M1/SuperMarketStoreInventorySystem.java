package M1;

import java.util.*;

abstract class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getValue() {
        return price * quantity;
    }

    abstract void display();
}

class Electronics extends Product {
    int warranty;

    Electronics(String name, double price, int quantity, int warranty) {
        super(name, price, quantity);
        this.warranty = warranty;
    }

    void display() {
        System.out.println(name + " - Price: " + price + ", Quantity: " + quantity + ", Warranty: " + warranty + " months");
    }
}

class Clothing extends Product {
    String size;

    Clothing(String name, double price, int quantity, String size) {
        super(name, price, quantity);
        this.size = size;
    }

    void display() {
        System.out.println(name + " - Price: " + price + ", Quantity: " + quantity + ", Size: " + size);
    }
}

public class SuperMarketStoreInventorySystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int num = Integer.parseInt(sc.nextLine());

        ArrayList<Product> inventory = new ArrayList<>();

        for (int i = 0; i < num; i++) {

            String line = sc.nextLine();
            String[] parts = line.split(",");

            String type = parts[0].trim();
            String name = parts[1].trim();
            double price = Double.parseDouble(parts[2].trim());
            int quantity = Integer.parseInt(parts[3].trim());

            if (type.equals("Electronics")) {

                int warranty = Integer.parseInt(parts[4].trim());
                Electronics e = new Electronics(name, price, quantity, warranty);
                inventory.add(e);

            } else if (type.equals("Clothing")) {

                String size = parts[4].trim();
                Clothing c = new Clothing(name, price, quantity, size);
                inventory.add(c);
            }

            System.out.println("Product added to inventory: " + name);
        }

        System.out.println();
        System.out.println("Inventory:");

        double total = 0;

        for (Product p : inventory) {
            p.display();
            total += p.getValue();
        }

        System.out.println();
        System.out.printf("Total value of the inventory: %.2f", total);

        sc.close();
    }
}