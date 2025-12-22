// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class StringIndexOutOfBoundException {
    public static void generateException(String text) {

        System.out.println(text.charAt(text.length()));
    }
    public static void handleException(String text) {

        try {
            System.out.println(text.charAt(text.length()));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("StringIndexOutOfBoundsException caught and handled");
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
    

