// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class ShoretstLongestWord {
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
        String word = "";
        int index = 0;

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
        String[][] arr = new String[words.length][2];

        for (int i = 0; i < words.length; i++) {
            arr[i][0] = words[i];
            arr[i][1] = String.valueOf(findLength(words[i]));
        }
        return arr;
    }
    static int[] findShortestLongest(String[][] arr) {
        int minIndex = 0;
        int maxIndex = 0;

        int minLen = Integer.parseInt(arr[0][1]);
        int maxLen = Integer.parseInt(arr[0][1]);

        for (int i = 1; i < arr.length; i++) {
            int len = Integer.parseInt(arr[i][1]);

            if (len < minLen) {
                minLen = len;
                minIndex = i;
            }
            if (len > maxLen) {
                maxLen = len;
                maxIndex = i;
            }
        }

        return new int[]{minIndex, maxIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String text = sc.nextLine();
        String[] words = splitText(text);
        String[][] wordLengthArray = createWordLengthArray(words);
        int[] result = findShortestLongest(wordLengthArray);
        System.out.println("\nShortest word: " + wordLengthArray[result[0]][0]);
        System.out.println("Length: " + wordLengthArray[result[0]][1]);

        System.out.println("\nLongest word: " + wordLengthArray[result[1]][0]);
        System.out.println("Length: " + wordLengthArray[result[1]][1]);

        sc.close();
    }
}
    

