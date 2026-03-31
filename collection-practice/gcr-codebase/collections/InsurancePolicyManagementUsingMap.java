package collections;

import java.util.*;
import java.time.LocalDate;

class Policy {
    String number;
    String holder;
    LocalDate expiry;
    String coverage;
    double premium;

    public Policy(String number, String holder, LocalDate expiry, String coverage, double premium) {
        this.number = number;
        this.holder = holder;
        this.expiry = expiry;
        this.coverage = coverage;
        this.premium = premium;
    }

    @Override
    public String toString() {
        return String.format("Policy[%s, %s, %s, %s, %.2f]", number, holder, expiry, coverage, premium);
    }
}

public class InsurancePolicyManagementUsingMap {
    public static void main(String[] args) {
        // Store policies in HashMap, LinkedHashMap, TreeMap
        Map<String, Policy> hashMap = new HashMap<>();
        Map<String, Policy> linkedHashMap = new LinkedHashMap<>();
        TreeMap<LocalDate, Policy> treeMap = new TreeMap<>();

        Policy p1 = new Policy("P001", "Alice", LocalDate.now().plusDays(10), "Health", 1000);
        Policy p2 = new Policy("P002", "Bob", LocalDate.now().plusDays(35), "Auto", 1200);
        Policy p3 = new Policy("P003", "Alice", LocalDate.now().plusDays(5), "Home", 900);

        // Add
        hashMap.put(p1.number, p1);
        hashMap.put(p2.number, p2);
        hashMap.put(p3.number, p3);

        linkedHashMap.put(p1.number, p1);
        linkedHashMap.put(p2.number, p2);
        linkedHashMap.put(p3.number, p3);

        treeMap.put(p1.expiry, p1);
        treeMap.put(p2.expiry, p2);
        treeMap.put(p3.expiry, p3);

        // Retrieve by policy number
        System.out.println("Retrieve by number P001: " + hashMap.get("P001"));

        // Policies expiring within 30 days
        System.out.println("\nExpiring in next 30 days:");
        LocalDate today = LocalDate.now();
        for (Policy p : hashMap.values()) {
            if (p.expiry.isBefore(today.plusDays(30))) {
                System.out.println(p);
            }
        }

        // Policies for specific policyholder
        System.out.println("\nPolicies for Alice:");
        for (Policy p : hashMap.values()) {
            if (p.holder.equalsIgnoreCase("Alice")) {
                System.out.println(p);
            }
        }

        // Remove expired policies (simulate 0 day)
        System.out.println("\nRemoving expired policies (if any)...");
        hashMap.values().removeIf(p -> p.expiry.isBefore(today));
        System.out.println("Remaining policies: " + hashMap.values());
    }
}