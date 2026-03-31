package regex;

import java.util.regex.*;

public class CurrencyExtractor {
    public static void main(String[] args) {

        String text = "The price is $45.99, and the discount is 10.50.";

        // Regex explanation:
        // \\$?        -> optional dollar sign
        // \\d+        -> one or more digits
        // \\.         -> decimal point
        // \\d{2}      -> exactly 2 decimal places
        String regex = "\\$?\\d+\\.\\d{2}";

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
