package collections;

import java.util.*;

public class BankingSystem {
    public static void main(String[] args) {
        // Accounts with balances
        Map<String, Double> accounts = new HashMap<>();
        accounts.put("ACC1", 5000.0);
        accounts.put("ACC2", 8000.0);
        accounts.put("ACC3", 3000.0);

        System.out.println("Accounts: " + accounts);

        // TreeMap: sort by balance (inverted)
        TreeMap<Double, String> sortedByBalance = new TreeMap<>();
        for (Map.Entry<String, Double> entry : accounts.entrySet()) {
            sortedByBalance.put(entry.getValue(), entry.getKey());
        }
        System.out.println("Accounts sorted by balance: " + sortedByBalance);

        // Queue for withdrawal requests
        Queue<String> withdrawalQueue = new LinkedList<>();
        withdrawalQueue.add("ACC1");
        withdrawalQueue.add("ACC3");
        withdrawalQueue.add("ACC2");

        System.out.println("Processing withdrawals in order:");
        while (!withdrawalQueue.isEmpty()) {
            System.out.println("Withdraw from: " + withdrawalQueue.remove());
        }
    }
}