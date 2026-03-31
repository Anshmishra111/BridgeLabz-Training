// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;  
public class NumberFormatException {
    public static void generateException(String text) {
        int number = Integer.parseInt(text);
        System.out.println("Converted number: " + number);
    }
    public static void handleException(String text) {
        try {
            int number = Integer.parseInt(text);
            System.out.println("Converted number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException caught and handled");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to convert to number: ");
        String text = sc.next();
        System.out.println("Calling method to generate exception:");
        generateException(text);
        System.out.println("\nCalling method to handle exception:");
        handleException(text);
        sc.close();
    }
    
}
