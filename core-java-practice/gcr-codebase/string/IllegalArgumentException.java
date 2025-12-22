// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class IllegalArgumentException {
    public static void generateException(String text) {
        System.out.println(text.substring(5, 2));
        
    }
    public static void handleException(String text) {
        try {
            System.out.println(text.substring(5, 2));
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException caught and handled");
        }
        catch(runtimeException e){
            System.out.println("RuntimeException caught and handled");
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
