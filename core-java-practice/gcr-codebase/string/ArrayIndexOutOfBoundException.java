// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class ArrayIndexOutOfBoundException {
    public static void generateException(String text) {
        char[] chars = text.toCharArray();
        System.out.println(chars[chars.length]);
    }
    public static void handleException(String text) {
        try {
            char[] chars = text.toCharArray();
            System.out.println(chars[chars.length]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught and handled");
        }
    }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a string: ");
            String text = sc.next();
            System.out.println("Calling method to generate exception:");
            generateException(text);
            System.out.println("\nCalling method to handle exception:");
            handleException(text);
            sc.close();
        }
    
}
