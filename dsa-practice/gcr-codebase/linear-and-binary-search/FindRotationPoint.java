package linear_and_binary_search;

public class FindRotationPoint {

    // Method to find index of smallest element (rotation point)
    static int findRotationPoint(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        // Binary search
        while (left < right) {
            int mid = (left + right) / 2;

            // If mid element is greater than rightmost,
            // smallest element is in right half
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } 
            // Otherwise, smallest element is in left half (including mid)
            else {
                right = mid;
            }
        }

        // left == right → index of smallest element
        return left;
    }

    public static void main(String[] args) {

        int[] arr = {4, 5, 6, 7, 0, 1, 2};

        int index = findRotationPoint(arr);

        System.out.println("Rotation point index: " + index);
        System.out.println("Smallest element: " + arr[index]);
    }
}
