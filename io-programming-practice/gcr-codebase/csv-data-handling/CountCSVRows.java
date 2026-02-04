package csv_data_handling;

import java.io.BufferedReader;
import java.io.FileReader;

public class CountCSVRows {

    public static void main(String[] args) {

        int count = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader("employees.csv"));
            String line;

            // Skip header row
            br.readLine();

            while ((line = br.readLine()) != null) {
                count++;
            }

            br.close();

            System.out.println("Total records: " + count);

        } catch (Exception e) {
            System.out.println("Error reading CSV file");
        }
    }
}
