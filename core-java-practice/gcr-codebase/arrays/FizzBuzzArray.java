// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class FizzBuzzArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] fizzBuzzArray = new String[n];
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzArray[i - 1] = "FizzBuzz";
            } else if (i % 3 == 0) {
                fizzBuzzArray[i - 1] = "Fizz";
            } else if (i % 5 == 0) {
                fizzBuzzArray[i - 1] = "Buzz";
            } else {
                fizzBuzzArray[i - 1] = Integer.toString(i);
            }
        }
        for (int i = 0; i < fizzBuzzArray.length; i++) {
            System.out.println(fizzBuzzArray[i]);
        }

        sc.close();
    }
    
}
