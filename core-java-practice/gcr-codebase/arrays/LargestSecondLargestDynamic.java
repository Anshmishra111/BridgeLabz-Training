// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class LargestSecondLargestDynamic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if (number < 0) {
            number = -number;
        }
        int maxDigit = 10;
        int[] digits = new int[maxDigit];
        int index = 0;
        while (number != 0) {
            if (index == maxDigit) {
                maxDigit = maxDigit + 10;

                int[] temp = new int[maxDigit];
                for (int i = 0; i < digits.length; i++) {
                    temp[i] = digits[i];
                }

                digits = temp;
            }
            digits[index] = number % 10;
            number = number / 10;
            index++;
        }
        int largest = -1;
        int secondLargest = -1;

        for (int i = 0; i < index; i++) {
            int digit = digits[i];

            if (digit > largest) {
                secondLargest = largest;
                largest = digit;
            } else if (digit > secondLargest && digit != largest) {
                secondLargest = digit;
            }
        }
        System.out.println("Largest digit: " + largest);
        System.out.println("Second largest digit: " + secondLargest);

        sc.close();
    }
}
