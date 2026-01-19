package linear_and_binary_search;

public class FirstAndLastOccurence {

    // Find first occurrence
    static int findFirst(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // search left side
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // Find last occurrence
    static int findLast(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // search right side
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;

        int first = findFirst(arr, target);
        int last = findLast(arr, target);

        if (first == -1) {
            System.out.println("Element not found");
        } else {
            System.out.println("First Occurrence Index: " + first);
            System.out.println("Last Occurrence Index: " + last);
        }
    }
}
