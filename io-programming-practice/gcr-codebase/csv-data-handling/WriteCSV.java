package csv_data_handling;

import java.io.FileWriter;

public class WriteCSV {

    public static void main(String[] args) {

        try {
            FileWriter writer = new FileWriter("employees.csv");

            // Writing employee records
            writer.write("ID,Name,Department,Salary\n");
            writer.write("1,Ansh,IT,50000\n");
            writer.write("2,Rahul,HR,45000\n");
            writer.write("3,Neha,Finance,55000\n");
            writer.write("4,Priya,Marketing,48000\n");
            writer.write("5,Amit,Sales,47000\n");

            writer.close();
            System.out.println("CSV file written successfully");

        } catch (Exception e) {
            System.out.println("Error writing CSV file");
        }
    }
}
