package linear_and_binary_search;

import java.util.Arrays;

public class LinearAndBinarySearchChallenge {

    // ---------- Linear Search ----------
    // Find first missing positive integer
    static int firstMissingPositive(int[] arr) {

        int n = arr.length;
        boolean[] visited = new boolean[n + 1];

        // Mark positive numbers
        for (int num : arr) {
            if (num > 0 && num <= n) {
                visited[num] = true;
            }
        }

        // Find first missing positive
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                return i;
            }
        }

        return n + 1;
    }

    // ---------- Binary Search ----------
    // Find index of target after sorting
    static int binarySearch(int[] arr, int target) {

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // ---------- Main ----------
    public static void main(String[] args) {

        int[] numbers = {3, 4, -1, 1};
        int target = 4;

        // Linear Search result
        int missing = firstMissingPositive(numbers);
        System.out.println("First Missing Positive Integer: " + missing);

        // Sort before Binary Search
        Arrays.sort(numbers);

        // Binary Search result
        int index = binarySearch(numbers, target);
        System.out.println("Index of target " + target + ": " + index);
    }
}
