package regex;

import java.util.regex.*;

public class DateExtractor {
    public static void main(String[] args) {

        String text = "The events are scheduled for 12/05/2023, 15/08/2024, and 29/02/2020.";

        // Regex explanation:
        // \\b              -> word boundary
        // (0[1-9]|[12][0-9]|3[01]) -> valid days (01–31)
        // /
        // (0[1-9]|1[0-2])  -> valid months (01–12)
        // /
        // \\d{4}          -> 4 digit year
        String regex = "\\b(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}\\b";

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
