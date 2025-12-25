package method;
import java.util.Scanner;
public class QuotientRemainderCalc {
    public static int[] findRemainderAndQuotient(int number, int divisor) {
        return new int[]{ number / divisor, number % divisor };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter numerator: ");
        int number = sc.nextInt();
        System.out.print("Enter denominator: ");
        int divisor = sc.nextInt();

        int[] result = findRemainderAndQuotient(number, divisor);
        System.out.println("Quotient: " + result[0]);
        System.out.println("Remainder: " + result[1]);
    }
}



