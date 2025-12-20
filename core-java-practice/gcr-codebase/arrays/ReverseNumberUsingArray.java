// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class ReverseNumberUsingArray {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int tempNumber = number < 0 ? -number : number;
        int count = 0;
        int temp = tempNumber;

        while (temp != 0) {
            count++;
            temp = temp / 10;
        }
        int[] digits = new int[count];
        for (int i = 0; i < count; i++) {
            digits[i] = tempNumber % 10;
            tempNumber = tempNumber / 10;
        }
        int[] reversed = new int[count];

        for (int i = 0; i < count; i++) {
            reversed[i] = digits[count - 1 - i];
        }
        System.out.print("Reversed number: ");
        if (number < 0) {
            System.out.print("-");
        }

        for (int i = 0; i < count; i++) {
            System.out.print(reversed[i]);
        }

        sc.close();
    }
}


