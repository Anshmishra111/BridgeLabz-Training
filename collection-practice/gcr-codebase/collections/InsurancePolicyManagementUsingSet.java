package collections;

import java.util.*;
import java.time.*;

class Policy implements Comparable<Policy> {
    String policyNumber;
    String holderName;
    LocalDate expiryDate;
    String coverageType;
    double premiumAmount;

    public Policy(String policyNumber, String holderName, LocalDate expiryDate, String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.holderName = holderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    @Override
    public String toString() {
        return String.format("Policy[%s, %s, %s, %s, %.2f]", 
                policyNumber, holderName, expiryDate, coverageType, premiumAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy)) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyNumber, policy.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public int compareTo(Policy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }
}

public class InsurancePolicyManagementUsingSet {
    public static void main(String[] args) {
        // Create policies
        Policy p1 = new Policy("P001", "Alice", LocalDate.now().plusDays(10), "Health", 1200.0);
        Policy p2 = new Policy("P002", "Bob", LocalDate.now().plusDays(40), "Auto", 800.0);
        Policy p3 = new Policy("P003", "Charlie", LocalDate.now().plusDays(20), "Home", 1500.0);
        Policy p4 = new Policy("P004", "Alice", LocalDate.now().plusDays(5), "Health", 1100.0);
        Policy p5 = new Policy("P001", "Alice", LocalDate.now().plusDays(10), "Health", 1200.0); // Duplicate

        // 1️⃣ HashSet — fast lookup
        Set<Policy> hashSet = new HashSet<>(Arrays.asList(p1, p2, p3, p4, p5));
        System.out.println("HashSet (Unique Policies):");
        hashSet.forEach(System.out::println);

        // 2️⃣ LinkedHashSet — insertion order
        Set<Policy> linkedHashSet = new LinkedHashSet<>(Arrays.asList(p1, p2, p3, p4, p5));
        System.out.println("\nLinkedHashSet (Insertion Order):");
        linkedHashSet.forEach(System.out::println);

        // 3️⃣ TreeSet — sorted by expiry date
        Set<Policy> treeSet = new TreeSet<>(Arrays.asList(p1, p2, p3, p4, p5));
        System.out.println("\nTreeSet (Sorted by Expiry Date):");
        treeSet.forEach(System.out::println);

        // Retrieve: Policies expiring soon (within 30 days)
        System.out.println("\nPolicies expiring within 30 days:");
        LocalDate today = LocalDate.now();
        for (Policy p : treeSet) {
            if (p.expiryDate.isBefore(today.plusDays(30))) {
                System.out.println(p);
            }
        }

        // Retrieve: Policies by coverage type
        String searchCoverage = "Health";
        System.out.println("\nPolicies with coverage type = " + searchCoverage + ":");
        for (Policy p : hashSet) {
            if (p.coverageType.equalsIgnoreCase(searchCoverage)) {
                System.out.println(p);
            }
        }

        // Check for duplicate policies (by policy number)
        Set<String> seenPolicyNumbers = new HashSet<>();
        System.out.println("\nChecking for duplicates:");
        for (Policy p : hashSet) {
            if (!seenPolicyNumbers.add(p.policyNumber)) {
                System.out.println("Duplicate found: " + p);
            }
        }

        // Simple performance demo
        comparePerformance();
    }

    public static void comparePerformance() {
        int N = 100000;
        List<Policy> policyList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            policyList.add(new Policy("PN" + i, "Holder" + i, LocalDate.now().plusDays(i % 365), "Health", 1000.0));
        }

        System.out.println("\n--- Performance Comparison ---");

        long start = System.nanoTime();
        Set<Policy> hashSet = new HashSet<>(policyList);
        long end = System.nanoTime();
        System.out.println("HashSet add: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        Set<Policy> linkedHashSet = new LinkedHashSet<>(policyList);
        end = System.nanoTime();
        System.out.println("LinkedHashSet add: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        Set<Policy> treeSet = new TreeSet<>(policyList);
        end = System.nanoTime();
        System.out.println("TreeSet add: " + (end - start) / 1e6 + " ms");
    }
}