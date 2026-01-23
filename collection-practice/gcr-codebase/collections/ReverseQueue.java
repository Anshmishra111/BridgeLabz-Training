package collections;

import java.util.*;

public class ReverseQueue {

    static void reverseQueue(Queue<Integer> queue) {

        if (queue.isEmpty()) {
            return;
        }

        int front = queue.remove();   // remove front
        reverseQueue(queue);          // reverse rest
        queue.add(front);             // add back
    }

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);
        queue.add(20);
        queue.add(30);

        reverseQueue(queue);

        System.out.println(queue);
    }
}
