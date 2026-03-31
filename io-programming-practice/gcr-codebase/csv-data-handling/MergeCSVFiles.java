package csv_data_handling;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MergeCSVFiles {

    public static void main(String[] args) {

        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String output = "merged_students.csv";

        Map<String, String[]> marksMap = new HashMap<>();

        try {
            // Read students2.csv (ID → Marks, Grade)
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            br2.readLine(); // skip header

            String line;
            while ((line = br2.readLine()) != null) {
                String[] data = line.split(",");
                marksMap.put(data[0], new String[]{data[1], data[2]});
            }
            br2.close();

            // Read students1.csv and merge
            BufferedReader br1 = new BufferedReader(new FileReader(file1));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));

            bw.write("id,name,age,marks,grade");
            bw.newLine();

            br1.readLine(); // skip header

            while ((line = br1.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0];

                String marks = "NA";
                String grade = "NA";

                if (marksMap.containsKey(id)) {
                    marks = marksMap.get(id)[0];
                    grade = marksMap.get(id)[1];
                }

                bw.write(id + "," + data[1] + "," + data[2] + "," + marks + "," + grade);
                bw.newLine();
            }

            br1.close();
            bw.close();

            System.out.println("✅ CSV files merged successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
