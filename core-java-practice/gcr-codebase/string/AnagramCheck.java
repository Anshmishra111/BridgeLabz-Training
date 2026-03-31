// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class AnagramCheck {
        static boolean areAnagrams(String text1, String text2) {
        if (text1.length() != text2.length()) {
            return false;
        }
        int[] freq1 = new int[256];
        int[] freq2 = new int[256];
        int index = 0;
        try {
            while (true) {
                char ch = text1.charAt(index);
                freq1[ch]++;
                index++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            
        }
        index = 0;
        try {
            while (true) {
                char ch = text2.charAt(index);
                freq2[ch]++;
                index++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        for (int i = 0; i < 256; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first text: ");
        String text1 = sc.nextLine();
        System.out.print("Enter second text: ");
        String text2 = sc.nextLine();
        boolean result = areAnagrams(text1, text2);
        if (result) {
            System.out.println("\nThe given texts are ANAGRAMS.");
        } else {
            System.out.println("\nThe given texts are NOT anagrams.");
        }

        sc.close();
    }
}

    

