package method;
import java.util.Scanner;
public class ChocolateDistribution {
    public static int[] findRemainderAndQuotient(int number, int divisor) {
        return new int[] { number / divisor, number % divisor };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of chocolates: ");
        int chocolates = sc.nextInt();
        System.out.print("Enter number of children: ");
        int children = sc.nextInt();
        int[] result = findRemainderAndQuotient(chocolates, children);
        System.out.println("Each child gets " + result[0] + " chocolates.");
        System.out.println("Remaining chocolates: " + result[1]);
    }
}

