package collections;

import java.util.*;

public class NthFromEnd {

    static String findNthFromEnd(LinkedList<String> list, int n) {

        Iterator<String> fast = list.iterator();
        Iterator<String> slow = list.iterator();

        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (!fast.hasNext()) {
                return "Invalid N";
            }
            fast.next();
        }

        // Move both pointers
        while (fast.hasNext()) {
            fast.next();
            slow.next();
        }

        return slow.next();
    }

    public static void main(String[] args) {

        LinkedList<String> list =
                new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E"));

        int n = 2;

        System.out.println(findNthFromEnd(list, n));
    }
}
