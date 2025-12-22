// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class FrequencyUsingUniqueChars {
    static int findLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return count;
    }
    static char[] uniqueCharacters(String text) {

        int len = findLength(text);
        char[] temp = new char[len];
        int uniqueCount = 0;

        for (int i = 0; i < len; i++) {
            char current = text.charAt(i);
            boolean isUnique = true;

            for (int j = 0; j < i; j++) {
                if (current == text.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                temp[uniqueCount++] = current;
            }
        }

        char[] unique = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            unique[i] = temp[i];
        }

        return unique;
    }
    static String[][] findFrequency(String text) {

        int[] freq = new int[256];
        int index = 0;
        try {
            while (true) {
                char ch = text.charAt(index);
                freq[ch]++;
                index++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        char[] unique = uniqueCharacters(text);
        String[][] result = new String[unique.length][2];

        for (int i = 0; i < unique.length; i++) {
            result[i][0] = String.valueOf(unique[i]);
            result[i][1] = String.valueOf(freq[unique[i]]);
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = sc.nextLine();
        String[][] frequencyTable = findFrequency(input);
        System.out.println("\nCharacter\tFrequency");
        System.out.println("------------------------");
        for (int i = 0; i < frequencyTable.length; i++) {
            System.out.println(
                    frequencyTable[i][0] + "\t\t" +
                    frequencyTable[i][1]
            );
        }

        sc.close();
    }
}

    

