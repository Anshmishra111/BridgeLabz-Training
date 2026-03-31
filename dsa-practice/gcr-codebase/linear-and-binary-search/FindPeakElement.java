package linear_and_binary_search;

public class FindPeakElement {

    // Method to find a peak element index
    static int findPeak(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // Check if mid is a peak
            boolean leftOk = (mid == 0) || (arr[mid] > arr[mid - 1]);
            boolean rightOk = (mid == arr.length - 1) || (arr[mid] > arr[mid + 1]);

            if (leftOk && rightOk) {
                return mid; // peak found
            }

            // If left neighbor is greater, search left half
            if (mid > 0 && arr[mid] < arr[mid - 1]) {
                right = mid - 1;
            }
            // Else search right half
            else {
                left = mid + 1;
            }
        }

        return -1; // should not happen for valid input
    }

    public static void main(String[] args) {

        int[] arr = {1, 3, 20, 4, 1, 0};

        int peakIndex = findPeak(arr);

        System.out.println("Peak element index: " + peakIndex);
        System.out.println("Peak element value: " + arr[peakIndex]);
    }
}
