import java.util.Scanner;

public class FactorialNumUsingForLoop {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int number = sc.nextInt();
        if (number <= 0) {
            System.out.println("Please enter a natural number (greater than 0)");
        } else {

            long factorial = 1;

            for (int i = 1; i <= number; i++) {
                factorial = factorial * i;
            }

            System.out.println("Factorial of " + number + " is " + factorial);
        }

        sc.close();
    }
}


       