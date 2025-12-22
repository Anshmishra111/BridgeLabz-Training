// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class SplitTextComparison {
    static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        
        }
        return count;
    }
    static String[] customSplit(String text) {
        int len = findLength(text);
        int wordCount = 1;
        int[] spaceIndex = new int[len];
        int spaceCount = 0;

        for (int i = 0; i < len; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndex[spaceCount++] = i;
                wordCount++;
            }
        }
        String[] words = new String[wordCount];

        int start = 0;
        for (int i = 0; i < wordCount; i++) {
            int end = (i < spaceCount) ? spaceIndex[i] : len;
            String word = "";

            for (int j = start; j < end; j++) {
                word += text.charAt(j);
            }

            words[i] = word;
            start = end + 1;
        }

        return words;
    }
    static boolean compareArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    } public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = sc.nextLine();
        String[] customWords = customSplit(text);
        String[] builtInWords = text.split(" ");
        boolean result = compareArrays(customWords, builtInWords);
        System.out.println("\nWords using custom split method:");
        for (String word : customWords) {
            System.out.println(word);
        }

        System.out.println("\nWords using built-in split() method:");
        for (String word : builtInWords) {
            System.out.println(word);
        }

        System.out.println("\nAre both results same? " + result);

        sc.close();
    }
}

    

