// package core-java-practice.gcr-codebase.string;
import java.util.Scanner;
public class VowelConsonantCount {
    static String checkCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char) (ch + 32);
        }
        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || 
                ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        }

        return "Not a Letter";
    }

    static int[] findVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        int index = 0;

        try {
            while (true) {
                char ch = text.charAt(index);
                String result = checkCharacter(ch);

                if (result.equals("Vowel")) {
                    vowels++;
                } else if (result.equals("Consonant")) {
                    consonants++;
                }
                index++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }

        return new int[]{vowels, consonants};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = sc.nextLine();
        int[] count = findVowelsAndConsonants(input);
        System.out.println("\nNumber of Vowels: " + count[0]);
        System.out.println("Number of Consonants: " + count[1]);

        sc.close();
    }
}

    

