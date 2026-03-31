package stack_queue_hashing;

import java.util.Stack;

public class SortStackUsingRecursion {
 // Function to sort the stack
    public static void sortStack(Stack<Integer> stack) {
        // Base case
        if (stack.isEmpty()) {
            return;
        }

        // Step 1: Pop top element
        int top = stack.pop();

        // Step 2: Sort remaining stack
        sortStack(stack);

        // Step 3: Insert popped element at correct position
        insertInSortedOrder(stack, top);
    }

    // Helper function to insert element in sorted order
    private static void insertInSortedOrder(Stack<Integer> stack, int element) {
        // Base case: correct position found
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }

        // Remove top and recur
        int top = stack.pop();
        insertInSortedOrder(stack, element);

        // Push back removed element
        stack.push(top);
    }

    // Main method
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(30);
        stack.push(10);
        stack.push(20);
        stack.push(5);

        System.out.println("Original Stack: " + stack);

        sortStack(stack);

        System.out.println("Sorted Stack:   " + stack);
    }
}

