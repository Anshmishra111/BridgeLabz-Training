import java.util.Scanner;

public class FactorialNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num < 0) {
            System.out.println("Please enter a positive integer");
        } else {

            long factorial = 1;
            int i = 1;

            while (i <= num) {
                factorial = factorial * i;
                i++;
            }

            System.out.println("Factorial of " + num + " is " + factorial);
        }

        sc.close();
    }
}

    
