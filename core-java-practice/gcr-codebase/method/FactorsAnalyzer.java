package method;
import java.util.*;
public class FactorsAnalyzer {
	public static int[] findFactors(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) if (n % i == 0) count++;
        int[] factors = new int[count];
        int index = 0;
        for (int i = 1; i <= n; i++) if (n % i == 0) factors[index++] = i;
        return factors;
    }

    public static int sum(int[] arr) {
        int s = 0; for (int val : arr) s += val; return s;
    }

    public static long product(int[] arr) {
        long p = 1; for (int val : arr) p *= val; return p;
    }

    public static double sumOfSquares(int[] arr) {
        double s = 0;
        for (int val : arr) s += Math.pow(val, 2);
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = sc.nextInt();
        int[] factors = findFactors(n);
        System.out.println("Factors: " + Arrays.toString(factors));
        System.out.println("Sum: " + sum(factors));
        System.out.println("Product: " + product(factors));
        System.out.println("Sum of Squares: " + sumOfSquares(factors));
    }
}

