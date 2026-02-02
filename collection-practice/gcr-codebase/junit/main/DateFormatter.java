package BridgeLabz_Training.Junit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFormatter {

    // Converts yyyy-MM-dd → dd-MM-yyyy
    public String formatDate(String inputDate) {

        try {
            LocalDate date = LocalDate.parse(inputDate); // expects yyyy-MM-dd
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return date.format(formatter);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }
    }
}
