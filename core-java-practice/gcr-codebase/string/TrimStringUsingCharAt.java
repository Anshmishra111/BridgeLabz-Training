// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class TrimStringUsingCharAt {
    static int[] findTrimIndexes(String str) {
        int start = 0;
        int end = 0;

        try {
            while (true) {
                str.charAt(end);
                end++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            end = end - 1;
        }
        while (start <= end && str.charAt(start) == ' ') {
            start++;
        }

        while (end >= start && str.charAt(end) == ' ') {
            end--;
        }

        return new int[]{start, end};
    }
    static String createSubstring(String str, int start, int end) {
        String result = "";

        for (int i = start; i <= end; i++) {
            result = result + str.charAt(i);
        }

        return result;
    }
    static boolean compareStrings(String s1, String s2) {

        int i = 0;
        try {
            while (true) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }
                i++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        try {
            s1.charAt(i);
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            try {
                s2.charAt(i);
                return false;
            } catch (StringIndexOutOfBoundsException ex) {
                return true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = sc.nextLine();
        int[] indexes = findTrimIndexes(input);
        String customTrimmed = createSubstring(input, indexes[0], indexes[1]);
        String builtInTrimmed = input.trim();
        boolean result = compareStrings(customTrimmed, builtInTrimmed);
        System.out.println("\nTrimmed string using custom method:");
        System.out.println("'" + customTrimmed + "'");

        System.out.println("\nTrimmed string using built-in trim():");
        System.out.println("'" + builtInTrimmed + "'");

        System.out.println("\nAre both strings equal? " + result);

        sc.close();
    }
}

    

