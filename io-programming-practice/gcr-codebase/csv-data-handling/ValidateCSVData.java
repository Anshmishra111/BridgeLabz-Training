package csv_data_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidateCSVData {

    // Email regex
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Phone number regex (exactly 10 digits)
    private static final String PHONE_REGEX = "^[0-9]{10}$";

    public static void main(String[] args) {

        String filePath = "employees.csv";

        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Pattern phonePattern = Pattern.compile(PHONE_REGEX);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            int rowNumber = 0;

            while ((line = br.readLine()) != null) {
                rowNumber++;

                // Skip header
                if (rowNumber == 1) {
                    continue;
                }

                String[] data = line.split(",");

                String email = data[2];
                String phone = data[3];

                boolean emailValid = emailPattern.matcher(email).matches();
                boolean phoneValid = phonePattern.matcher(phone).matches();

                if (!emailValid || !phoneValid) {
                    System.out.println("❌ Invalid Row at line " + rowNumber);
                    System.out.println("Data: " + line);

                    if (!emailValid) {
                        System.out.println("Error: Invalid Email Format");
                    }
                    if (!phoneValid) {
                        System.out.println("Error: Phone number must contain exactly 10 digits");
                    }

                    System.out.println("-----------------------------------");
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file");
            e.printStackTrace();
        }
    }
}
