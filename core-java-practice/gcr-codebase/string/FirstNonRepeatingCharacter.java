// package core-java-practice.gcr-codebase.string;

public class FirstNonRepeatingCharacter {

    static char findFirstNonRepeatingChar(String text) {
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
        index = 0;
        try {
            while (true) {
                char ch = text.charAt(index);
                if (freq[ch] == 1) {
                    return ch;
                }
                index++;
            }
        } catch (StringIndexOutOfBoundsException e) {
        }

        return '\0'; 
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = sc.nextLine();
        char result = findFirstNonRepeatingChar(input);
        if (result != '\0') {
            System.out.println("\nFirst non-repeating character: " + result);
        } else {
            System.out.println("\nNo non-repeating character found.");
        }

        sc.close();
    }
}

    

