package csv_data_handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UpdateSalaryCSV {

    public static void main(String[] args) {

        String inputFile = "employees.csv";
        String outputFile = "updated_employees.csv";

        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;

            // Read and write header
            line = br.readLine();
            bw.write(line);
            bw.newLine();

            // Read remaining records
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String department = data[2];
                double salary = Double.parseDouble(data[3]);

                // Increase salary by 10% for IT department
                if (department.equalsIgnoreCase("IT")) {
                    salary = salary + (salary * 0.10);
                }

                // Write updated record
                bw.write(data[0] + "," + data[1] + "," + data[2] + "," + (int) salary);
                bw.newLine();
            }

            System.out.println("Salary updated successfully ✅");
            System.out.println("Updated file created: " + outputFile);

        } catch (IOException e) {
            System.out.println("Error processing CSV file");
            e.printStackTrace();
        }
    }
}
