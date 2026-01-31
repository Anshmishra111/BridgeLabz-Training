package regex;

import java.util.regex.*;

public class CreditCardValidation {
    public static void main(String[] args) {

        String[] cards = {
                "4123456789012345", // Visa
                "5123456789012345", // MasterCard
                "6123456789012345", // Invalid
                "41234",            // Invalid
                "5234567890123456"  // MasterCard
        };

        // Regex:
        // 4[0-9]{15} -> Visa (starts with 4, total 16 digits)
        // 5[0-9]{15} -> MasterCard (starts with 5, total 16 digits)
        String regex = "^(4[0-9]{15}|5[0-9]{15})$";

        Pattern pattern = Pattern.compile(regex);

        for (String card : cards) {

            Matcher matcher = pattern.matcher(card);

            if (matcher.matches()) {
                System.out.println(card + " → Valid Card");
            } else {
                System.out.println(card + " → Invalid Card");
            }
        }
    }
}
