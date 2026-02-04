package csv_data_handling;

import java.io.BufferedReader;
import java.io.FileReader;

public class FilterCSV {

    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("students.csv"));
            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int marks = Integer.parseInt(data[3]);

                if (marks > 80) {
                    System.out.println("ID: " + data[0]
                            + ", Name: " + data[1]
                            + ", Marks: " + marks);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error reading CSV file");
        }
    }
}
