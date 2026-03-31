package regex;

import java.util.regex.*;

public class LanguageExtractor {
    public static void main(String[] args) {

        String text =
            "I love Java, Python, and JavaScript, but I haven't tried Go yet.";

        // Add languages inside regex using |
        String regex = "\\b(Java|Python|JavaScript|Go)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        boolean first = true;

        while (matcher.find()) {
            if (!first) {
                System.out.print(", ");
            }
            System.out.print(matcher.group());
            first = false;
        }
    }
}
