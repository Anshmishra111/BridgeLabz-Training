package csv_data_handling;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class DetectDuplicateCSV {

    public static void main(String[] args) {

        String filePath = "employees.csv";

        Set<String> seenIds = new HashSet<>();
        boolean duplicateFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine(); // skip header

            System.out.println("Duplicate Records:\n");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String id = data[0].trim();

                if (seenIds.contains(id)) {
                    System.out.println(line);
                    duplicateFound = true;
                } else {
                    seenIds.add(id);
                }
            }

            if (!duplicateFound) {
                System.out.println("No duplicate records found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
