package collections;

import java.util.*;

public class VotingSystem {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Alice", 5);
        hashMap.put("Bob", 3);
        hashMap.put("Charlie", 7);

        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(hashMap);
        TreeMap<String, Integer> treeMap = new TreeMap<>(hashMap);

        System.out.println("Votes (HashMap): " + hashMap);
        System.out.println("Votes in insertion order (LinkedHashMap): " + linkedHashMap);
        System.out.println("Votes sorted by candidate name (TreeMap): " + treeMap);
    }
}