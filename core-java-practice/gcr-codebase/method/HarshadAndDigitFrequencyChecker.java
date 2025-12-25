package method;
import java.util.*;
public class HarshadAndDigitFrequencyChecker {
    public static int countDigits(int number) {
        return Integer.toString(number).length();
    }

    public static int[] extractDigits(int number) {
        String numStr = Integer.toString(number);
        int[] digits = new int[numStr.length()];
        for (int i = 0; i < numStr.length(); i++) {
            digits[i] = numStr.charAt(i) - '0';
        }
        return digits;
    }

    public static int sumOfDigits(int[] digits) {
        int sum = 0;
        for (int digit : digits) sum += digit;
        return sum;
    }

    public static double sumOfSquares(int[] digits) {
        double sum = 0;
        for (int digit : digits) sum += Math.pow(digit, 2);
        return sum;
    }

    public static boolean isHarshad(int number, int[] digits) {
        int sum = sumOfDigits(digits);
        return number % sum == 0;
    }

    public static int[][] digitFrequency(int[] digits) {
        int[] freq = new int[10];
        for (int digit : digits) freq[digit]++;
        
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (freq[i] > 0)
                result.add(new int[]{i, freq[i]});
        }

        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = sc.nextInt();

        int[] digits = extractDigits(number);

        System.out.println("Digit Count: " + countDigits(number));
        System.out.println("Sum of Digits: " + sumOfDigits(digits));
        System.out.println("Sum of Squares of Digits: " + sumOfSquares(digits));
        System.out.println("Is Harshad Number? " + isHarshad(number, digits));

        int[][] freq = digitFrequency(digits);
        System.out.println("Digit Frequency:");
        for (int[] row : freq) {
            System.out.println("Digit: " + row[0] + ", Frequency: " + row[1]);
        }
    }
}

