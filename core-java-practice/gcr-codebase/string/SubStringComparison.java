// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class SubStringComparison {
    public static String substringUsingCharAt(String text, int start, int end) {
        String result = "";
        for (int i = start; i < end; i++) {
            result = result + text.charAt(i);
        }

        return result;
    }
    public static boolean compareUsingCharAt(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.next();

        System.out.print("Enter start index: ");
        int start = sc.nextInt();

        System.out.print("Enter end index: ");
        int end = sc.nextInt();
        String subCharAt = substringUsingCharAt(text, start, end);
        String subBuiltIn = text.substring(start, end);
        boolean isEqual = compareUsingCharAt(subCharAt, subBuiltIn);
        System.out.println("\nSubstring using charAt(): " + subCharAt);
        System.out.println("Substring using built-in substring(): " + subBuiltIn);
        System.out.println("Are both substrings equal? " + isEqual);

        sc.close();
    }
}

    

