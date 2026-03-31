package collectors;

import java.util.*;
import java.util.stream.*;

// Order class
class Order {
    String customer;
    double amount;

    Order(String customer, double amount) {
        this.customer = customer;
        this.amount = amount;
    }
}

// Main class
public class OrderRevenueSummary {
    public static void main(String[] args) {

        List<Order> orders = Arrays.asList(
            new Order("Amit", 2500),
            new Order("Rahul", 1500),
            new Order("Amit", 3000),
            new Order("Neha", 2000),
            new Order("Rahul", 1800)
        );

        Map<String, Double> totalRevenue =
            orders.stream()
                .collect(Collectors.groupingBy(
                    o -> o.customer,
                    Collectors.summingDouble(o -> o.amount)
                ));

        totalRevenue.forEach((customer, sum) ->
            System.out.println(customer + " : " + sum)
        );
    }
}
