import java.util.Scanner;

public class SumOfNaturalNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("Please enter a natural number (greater than 0)");
        } else {

            int sumByLoop = 0;
            int i = 1;

            while (i <= n) {
                sumByLoop = sumByLoop + i;
                i++;
            }

            int sumByFormula = n * (n + 1) / 2;

            System.out.println("Sum using while loop = " + sumByLoop);
            System.out.println("Sum using formula = " + sumByFormula);

            if (sumByLoop == sumByFormula) {
                System.out.println("Both results are correct and equal");
            } else {
                System.out.println("Results are not equal");
            }
        }

        sc.close();
    }
}

        
