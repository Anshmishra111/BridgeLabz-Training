import java.util.Scanner;

public class SumOfNaturalNumForLoop {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Please enter a natural number (greater than 0)");
        } else {
            int sumByLoop = 0;

            for (int i = 1; i <= n; i++) {
                sumByLoop = sumByLoop + i;
            }

            int sumByFormula = n * (n + 1) / 2;

            System.out.println("Sum using for loop = " + sumByLoop);
            System.out.println("Sum using formula = " + sumByFormula);

            // Compare results
            if (sumByLoop == sumByFormula) {
                System.out.println("Both results are correct and equal");
            } else {
                System.out.println("Results are not equal");
            }
        }

        sc.close();
    }
}


