package runtime_analysis_and_big_O_notation;
import java.util.Arrays;

public class SearchComparison {

    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == x) return i;
        return -1;
    }

    static int binarySearch(int[] a, int x) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] == x) return m;
            if (a[m] < x) l = m + 1;
            else r = m - 1;
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] data = new int[100000];
        for (int i = 0; i < data.length; i++)
            data[i] = i;

        int target = -1; // worst case

        long start = System.nanoTime();
        linearSearch(data, target);
        long linearTime = System.nanoTime() - start;

        Arrays.sort(data);
        start = System.nanoTime();
        binarySearch(data, target);
        long binaryTime = System.nanoTime() - start;

        System.out.println("Linear Search Time  : " + linearTime + " ns");
        System.out.println("Binary Search Time : " + binaryTime + " ns");
    }
}
