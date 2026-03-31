package scenario_based;

import java.util.*;

class LexicalTwist{

    // Method to check if string contains space
    public static boolean isInvalidWord(String word) {
        return word.contains(" ");
    }

    // Method to check reverse (case-insensitive)
    public static boolean isReverse(String s1, String s2) {
        StringBuilder sb = new StringBuilder(s1);
        return sb.reverse().toString().equalsIgnoreCase(s2);
    }

    // Method to check vowel
    public static boolean isVowel(char ch) {
        return "AEIOUaeiou".indexOf(ch) != -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first word");
        String first = sc.nextLine();

        if (isInvalidWord(first)) {
            System.out.println(first + " is an invalid word");
            return;
        }

        System.out.println("Enter the second word");
        String second = sc.nextLine();

        if (isInvalidWord(second)) {
            System.out.println(second + " is an invalid word");
            return;
        }

        // Case 1: Second word is reverse of first
        if (isReverse(first, second)) {

            StringBuilder sb = new StringBuilder(first);
            sb.reverse();
            String result = sb.toString().toLowerCase();

            result = result.replaceAll("[aeiou]", "@");
            System.out.println(result);

        } 
        // Case 2: Not reverse
        else {
            String combined = (first + second).toUpperCase();

            int vowelCount = 0, consonantCount = 0;

            for (char ch : combined.toCharArray()) {
                if (isVowel(ch))
                    vowelCount++;
                else
                    consonantCount++;
            }

            // Vowels > Consonants
            if (vowelCount > consonantCount) {
                LinkedHashSet<Character> vowels = new LinkedHashSet<>();
                for (char ch : combined.toCharArray()) {
                    if (isVowel(ch))
                        vowels.add(ch);
                    if (vowels.size() == 2)
                        break;
                }
                for (char c : vowels)
                    System.out.print(c);
            }
            // Consonants > Vowels
            else if (consonantCount > vowelCount) {
                LinkedHashSet<Character> consonants = new LinkedHashSet<>();
                for (char ch : combined.toCharArray()) {
                    if (!isVowel(ch))
                        consonants.add(ch);
                    if (consonants.size() == 2)
                        break;
                }
                for (char c : consonants)
                    System.out.print(c);
            }
            // Equal
            else {
                System.out.println("Vowels and consonants are equal");
            }
        }

        sc.close();
    }
}
