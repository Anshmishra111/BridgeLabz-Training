package regex;

import java.util.regex.*;

public class RepeatingWords {
    public static void main(String[] args) {

        String text = "This is is a repeated repeated word test.";

        // Regex explanation:
        // \\b(\\w+)  -> capture a word
        // \\s+       -> one or more spaces
        // \\1        -> same word repeated (backreference)
        String regex = "\\b(\\w+)\\s+\\1\\b";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        boolean first = true;

        while (matcher.find()) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(matcher.group(1)); // prints the repeated word
            first = false;
        }
    }
}
