package method;
import java.util.*;
public class RandomNumAnalyzer {
    public static int[] generate4DigitRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int)(Math.random() * 9000) + 1000;
        }
        return arr;
    }

    public static double[] findAverageMinMax(int[] numbers) {
        int sum = 0, min = numbers[0], max = numbers[0];
        for (int num : numbers) {
            sum += num;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return new double[]{ sum / 5.0, min, max };
    }

    public static void main(String[] args) {
        int[] nums = generate4DigitRandomArray(5);
        System.out.println("Generated numbers: " + Arrays.toString(nums));
        double[] result = findAverageMinMax(nums);
        System.out.println("Average: " + result[0] + ", Min: " + result[1] + ", Max: " + result[2]);
    }
}


