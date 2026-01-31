package scenario_based;

import java.util.Scanner;

class FlipKeyLogicalProblem {

    public static String CleanseAndInvert(String input) {

        // Validation: null or length < 6
        if (input == null || input.length() < 6) {
            return "";
        }

        // Validation: only alphabets allowed (no space, digit, special char)
        for (char ch : input.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return "";
            }
        }

        // Step 1: Convert to lowercase
        input = input.toLowerCase();

        // Step 2: Remove characters with even ASCII values
        StringBuilder filtered = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if ((int) ch % 2 != 0) {   // keep odd ASCII
                filtered.append(ch);
            }
        }

        // Step 3: Reverse the string
        filtered.reverse();

        // Step 4: Convert even index characters to uppercase
        for (int i = 0; i < filtered.length(); i++) {
            if (i % 2 == 0) {
                filtered.setCharAt(i,
                        Character.toUpperCase(filtered.charAt(i)));
            }
        }

        return filtered.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the word");
        String input = sc.nextLine();

        String result = CleanseAndInvert(input);

        if (result.equals("")) {
            System.out.println("Invalid Input");
        } else {
            System.out.println("The generated key is - " + result);
        }

        sc.close();
    }
}
