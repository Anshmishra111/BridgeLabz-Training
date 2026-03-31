package collections;

import java.util.*;

public class ShoppingCart {
    public static void main(String[] args) {
        // Product prices
        Map<String, Double> productPrices = new HashMap<>();
        productPrices.put("Book", 500.0);
        productPrices.put("Pen", 20.0);
        productPrices.put("Bag", 800.0);

        // Items added in order
        LinkedHashMap<String, Integer> cart = new LinkedHashMap<>();
        cart.put("Book", 1);
        cart.put("Pen", 3);
        cart.put("Bag", 1);

        System.out.println("Shopping Cart Order: " + cart);

        // Items sorted by price
        TreeMap<Double, String> sortedByPrice = new TreeMap<>();
        for (String product : productPrices.keySet()) {
            sortedByPrice.put(productPrices.get(product), product);
        }
        System.out.println("Products sorted by price: " + sortedByPrice);
    }
}