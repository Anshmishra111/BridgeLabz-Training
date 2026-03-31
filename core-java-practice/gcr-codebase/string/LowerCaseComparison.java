// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class LowerCaseComparison {
    static String toLowerCaseCustom(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + 32);
            }
            result = result + ch;
        }
        return result;
    }
    static boolean compareStrings(String s1, String s2) {
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
        System.out.print("Enter the complete text: ");
        String inputText = sc.nextLine();
        String builtInLower = inputText.toLowerCase();
        String customLower = toLowerCaseCustom(inputText);
        boolean isSame = compareStrings(builtInLower, customLower);
        System.out.println("\nLowercase using built-in method:");
        System.out.println(builtInLower);
        System.out.println("\nLowercase using custom method:");
        System.out.println(customLower);
        System.out.println("\nAre both lowercase strings same? " + isSame);

        sc.close();
    }
}

    

