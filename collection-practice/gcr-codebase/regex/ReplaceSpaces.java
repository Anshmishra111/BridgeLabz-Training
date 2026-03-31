package regex;

public class ReplaceSpaces {
    public static void main(String[] args) {

        String input = "This   is    an   example   with   multiple   spaces.";

        // \\s+  → matches one or more whitespace characters
        String output = input.replaceAll("\\s+", " ");

        System.out.println(output);
    }
}
