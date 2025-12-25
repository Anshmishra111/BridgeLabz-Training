package method;
import java.util.Scanner;
public class NumberCheckerUtility {
    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        return true;
    }

    public static boolean isNeon(int n) {
        int square = n * n;
        int sum = 0;
        while (square > 0) {
            sum += square % 10;
            square /= 10;
        }
        return sum == n;
    }

    public static boolean isSpy(int n) {
        int sum = 0, product = 1, digit;
        while (n > 0) {
            digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }
        return sum == product;
    }

    public static boolean isAutomorphic(int n) {
        int square = n * n;
        String numStr = Integer.toString(n);
        String squareStr = Integer.toString(square);
        return squareStr.endsWith(numStr);
    }

    public static boolean isBuzz(int n) {
        return n % 7 == 0 || n % 10 == 7;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to check: ");
        int number = sc.nextInt();

        System.out.println("Results for number: " + number);
        System.out.println("Is Prime? " + isPrime(number));
        System.out.println("Is Neon? " + isNeon(number));
        System.out.println("Is Spy? " + isSpy(number));
        System.out.println("Is Automorphic? " + isAutomorphic(number));
        System.out.println("Is Buzz? " + isBuzz(number));
        sc.close();
    }
}


