package regex;

import java.util.regex.*;

public class SocialSecurityNoValidation {
    public static void main(String[] args) {

        String text = "My SSN is 123-45-6789.";
        String invalid = "123456789";

        // Regex:
        // \\b      -> word boundary
        // \\d{3}   -> exactly 3 digits
        // -        -> hyphen
        // \\d{2}   -> exactly 2 digits
        // -        -> hyphen
        // \\d{4}   -> exactly 4 digits
        String regex = "\\b\\d{3}-\\d{2}-\\d{4}\\b";

        Pattern pattern = Pattern.compile(regex);

        // Check valid SSN inside text
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("✅ \"" + matcher.group() + "\" is valid");
        } else {
            System.out.println("No valid SSN found");
        }

        // Check invalid SSN
        if (!invalid.matches(regex)) {
            System.out.println("❌ \"" + invalid + "\" is invalid");
        }
    }
}
