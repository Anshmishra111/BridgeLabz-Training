// package core-java-practice.gcr-codebase.arrays;
import java.util.Scanner;
public class NumberAnalyzer {
    public static void main(String[] args) {
        // Implementation for NumberAnalyzer
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[5];
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = sc.nextInt();
        }
        System.out.println("\n--- Number Analysis Results ---");
        for (int i=0; i < numbers.length; i++) {
            int number = numbers[i];
            if(number > 0) {
            if (number % 2 == 0) {
                System.out.println(number + " is Even");
            } else {
                System.out.println(number + " is Odd");
            }
        }
            else if (number < 0) {
                System.out.println(number + " is Negative");
            
            } else {
                System.out.println(number + " is Zero");
            }
        }
        int first = numbers[0];
        int last = numbers[numbers.length - 1];
        System.out.println("\n--- First and Last Element Comparison ---");
        if (first==last) {
            System.out.println(first + " is both First and Last element");
        }
            else if (first> last) {
                System.out.println(first + " is Greater than " + last);
            } else  {
                System.out.println(last + " is Greater than " + first);
            } 
            
        
        sc.close();
    }

}
