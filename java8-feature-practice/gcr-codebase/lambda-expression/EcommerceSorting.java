package lambda_expression;

import java.util.*;

class Product {
    String name;
    double price;
    double rating;
    int discount;

    Product(String name, double price, double rating, int discount) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return name + " | Price: " + price +
               " | Rating: " + rating +
               " | Discount: " + discount + "%";
    }
}

public class EcommerceSorting {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 60000, 4.5, 10));
        products.add(new Product("Mobile", 30000, 4.7, 20));
        products.add(new Product("Headphones", 3000, 4.2, 30));

        // 🔹 Sort by Price (Low to High)
        products.sort((p1, p2) -> Double.compare(p1.price, p2.price));
        System.out.println("Sorted by Price:");
        products.forEach(System.out::println);

        // 🔹 Sort by Rating (High to Low)
        products.sort((p1, p2) -> Double.compare(p2.rating, p1.rating));
        System.out.println("\nSorted by Rating:");
        products.forEach(System.out::println);

        // 🔹 Sort by Discount (High to Low)
        products.sort((p1, p2) -> Integer.compare(p2.discount, p1.discount));
        System.out.println("\nSorted by Discount:");
        products.forEach(System.out::println);
    }
}
