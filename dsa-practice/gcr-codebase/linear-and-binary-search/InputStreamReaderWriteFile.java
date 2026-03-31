package linear_and_binary_search;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class InputStreamReaderWriteFile {

    public static void main(String[] args) {

        String fileName = "output.txt";

        try {
            // Step 1: Read input from console
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            // Step 2: Create FileWriter
            FileWriter fw = new FileWriter(fileName);

            String input;
            System.out.println("Enter text (type 'exit' to stop):");

            // Step 3: Read input until "exit"
            while (true) {
                input = br.readLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                // Step 4: Write input to file
                fw.write(input + "\n");
            }

            // Step 5: Close resources
            fw.close();
            br.close();
            isr.close();

            System.out.println("Data written to file successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
