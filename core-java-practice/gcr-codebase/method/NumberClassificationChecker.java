package method;
import java.util.Scanner;

public class NumberClassificationChecker {
    public static int sumOfDivisors(int number) {
        int sum = 0;
        for (int i = 1; i <= number / 2; i++)
            if (number % i == 0)
                sum += i;
        return sum;
    }

    public static boolean isPerfect(int number) {
        return sumOfDivisors(number) == number;
    }

    public static boolean isAbundant(int number) {
        return sumOfDivisors(number) > number;
    }

    public static boolean isDeficient(int number) {
        return sumOfDivisors(number) < number;
    }

    public static int factorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
    }

    public static boolean isStrong(int number) {
        int sum = 0, temp = number;
        while (temp > 0) {
            sum += factorial(temp % 10);
            temp /= 10;
        }
        return sum == number;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int number = sc.nextInt();

        System.out.println("Is Perfect? " + isPerfect(number));
        System.out.println("Is Abundant? " + isAbundant(number));
        System.out.println("Is Deficient? " + isDeficient(number));
        System.out.println("Is Strong? " + isStrong(number));
    }
}


