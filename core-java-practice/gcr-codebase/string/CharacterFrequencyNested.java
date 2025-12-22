// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class CharacterFrequencyNested {
    static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int len = chars.length;
        int[] freq = new int[len];
        for (int i = 0; i < len; i++) {
            freq[i] = 1;
        }
        for (int i = 0; i < len; i++) {
            if (chars[i] == '0') {
                continue;
            }

            for (int j = i + 1; j < len; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; 
                }
            }
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] != '0') {
                count++;
            }
        }
        String[] result = new String[count];
        int index = 0;

        for (int i = 0; i < len; i++) {
            if (chars[i] != '0') {
                result[index++] = chars[i] + " -> " + freq[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = sc.nextLine();
        String[] frequencyResult = findFrequency(input);
        System.out.println("\nCharacter Frequency:");
        System.out.println("---------------------");

        for (String entry : frequencyResult) {
            System.out.println(entry);
        }

        sc.close();
    }
}

    
