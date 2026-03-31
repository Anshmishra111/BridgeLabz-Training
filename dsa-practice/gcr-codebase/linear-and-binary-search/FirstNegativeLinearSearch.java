package linear_and_binary_search;

public class FirstNegativeLinearSearch {

    // Method to find first negative number
    static int findFirstNegative(int[] arr) {

        // Step 1: Traverse the array
        for (int i = 0; i < arr.length; i++) {

            // Step 2: Check if element is negative
            if (arr[i] < 0) {
                return i; // Step 3: Return index
            }
        }

        // Step 4: No negative number found
        return -1;
    }

    public static void main(String[] args) {

        int[] numbers = {5, 12, 7, -3, 8, -10};

        int index = findFirstNegative(numbers);

        if (index != -1) {
            System.out.println("First negative number found at index: " + index);
        } else {
            System.out.println("No negative number found");
        }
    }
}
