// package core-java-practice.gcr-codebase.string;

public class UpperCaseUsingCharAt {
    public static String toUpperCase(String input) {
        String result = "";
        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if(ch >= 'a' && ch <= 'z') {
                ch = (char)(ch - 32);
            }
            result += ch;
        }
        return result;
    }
    public static boolean compareStrings(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        String upperCaseResult = toUpperCase(input);
        String builtInUpperCase = input.toUpperCase();
        boolean isEqual = compareStrings(upperCaseResult, builtInUpperCase);
        System.out.println("Uppercase using charAt(): " + upperCaseResult);
        System.out.println("Uppercase using built-in toUpperCase(): " + builtInUpperCase);
        System.out.println("Are both uppercase strings equal? " + isEqual);
        sc.close();
    }
    
}
