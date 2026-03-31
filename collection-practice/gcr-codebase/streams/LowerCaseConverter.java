package streams;
import java.io.*;

public class LowerCaseConverter {

    public static void main(String[] args) {

        String sourceFile = "input.txt";
        String destinationFile = "output.txt";

        File input = new File(sourceFile);

        if (!input.exists()) {
            System.out.println("input.txt not found!");
            System.out.println("Create input.txt in project folder and run again.");
            return;
        }

        try (
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(sourceFile), "UTF-8")
            );
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(destinationFile), "UTF-8")
            )
        ) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line.toLowerCase());
                bw.newLine();
            }

            System.out.println("Conversion completed successfully!");
            System.out.println("Check output.txt");

        } catch (IOException e) {
            System.out.println("Error while processing the file.");
            e.printStackTrace();
        }
    }
}
