// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class CharacterFrequency {
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
        int uniqueCount = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                uniqueCount++;
            }
        }
        String[][] result = new String[uniqueCount][2];
        int row = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                result[row][0] = String.valueOf((char) i);
                result[row][1] = String.valueOf(freq[i]);
                row++;
            }
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
            System.out.println(frequencyTable[i][0] + "\t\t" + frequencyTable[i][1]);
        }

        sc.close();
    }
}

    
