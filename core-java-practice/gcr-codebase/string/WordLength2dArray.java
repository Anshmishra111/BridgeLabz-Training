// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;   
public class WordLength2dArray {
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
    static String[] splitText(String text) {
        int len = findLength(text);

        int wordCount = 1;
        for (int i = 0; i < len; i++) {
            if (text.charAt(i) == ' ') {
                wordCount++;
            }
        }

        String[] words = new String[wordCount];

        int index = 0;
        String word = "";

        for (int i = 0; i < len; i++) {
            if (text.charAt(i) != ' ') {
                word = word + text.charAt(i);
            } else {
                words[index++] = word;
                word = "";
            }
        }
        words[index] = word;

        return words;
    }
    static String[][] createWordLengthArray(String[] words) {
        String[][] result = new String[words.length][2];

        for (int i = 0; i < words.length; i++) {
            int length = findLength(words[i]);
            result[i][0] = words[i];
            result[i][1] = String.valueOf(length);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = sc.nextLine();
        String[] words = splitText(text);
        String[][] wordLengthArray = createWordLengthArray(words);
        System.out.println("\nWord\t\tLength");
        System.out.println("-----------------------");

        for (int i = 0; i < wordLengthArray.length; i++) {
            String word = wordLengthArray[i][0];
            int length = Integer.parseInt(wordLengthArray[i][1]);
            System.out.println(word + "\t\t" + length);
        }

        sc.close();
    }
}

    

