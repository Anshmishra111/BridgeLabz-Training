package regex;

import java.util.regex.*;

public class CensorWords {
    public static void main(String[] args) {

        String input = "This is a damn bad example with some stupid words.";

        // List of bad words
        String regex = "\\b(damn|stupid)\\b";

        // (?i) makes it case-insensitive
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        String output = matcher.replaceAll("****");

        System.out.println(output);
    }
}
