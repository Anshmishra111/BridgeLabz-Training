package stack_queue_hashing;

import java.util.Stack;

public class QueueUsingStacks {

    Stack<Integer> stackEnqueue = new Stack<>();
    Stack<Integer> stackDequeue = new Stack<>();

    // Enqueue operation (O(1))
    public void enqueue(int value) {
        stackEnqueue.push(value);
        System.out.println("Enqueued: " + value);
    }

    // Dequeue operation (Amortized O(1))
    public int dequeue() {
        if (stackDequeue.isEmpty()) {
            // Transfer elements from enqueue stack to dequeue stack
            while (!stackEnqueue.isEmpty()) {
                stackDequeue.push(stackEnqueue.pop());
            }
        }

        if (stackDequeue.isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        return stackDequeue.pop();
    }

    // Display queue elements
    public void display() {
        System.out.print("Queue: ");

        // Print dequeue stack (front elements)
        for (int i = stackDequeue.size() - 1; i >= 0; i--) {
            System.out.print(stackDequeue.get(i) + " ");
        }

        // Print enqueue stack (rear elements)
        for (int i = 0; i < stackEnqueue.size(); i++) {
            System.out.print(stackEnqueue.get(i) + " ");
        }

        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();

        queue.enqueue(40);
        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();
    }
}
