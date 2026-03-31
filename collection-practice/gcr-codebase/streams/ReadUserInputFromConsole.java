package streams;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class ReadUserInputFromConsole {
	
	    public static void main(String[] args) {

	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        FileWriter fw = null;

	        try {
	            // Read user input
	            System.out.print("Enter your name: ");
	            String name = br.readLine();

	            System.out.print("Enter your age: ");
	            String age = br.readLine();

	            System.out.print("Enter your favorite programming language: ");
	            String language = br.readLine();

	            // Write to file
	            fw = new FileWriter("user_info.txt");
	            fw.write("Name: " + name + "\n");
	            fw.write("Age: " + age + "\n");
	            fw.write("Favorite Language: " + language + "\n");

	            System.out.println("\nData saved successfully to user_info.txt");

	        } catch (IOException e) {
	            System.out.println("Error occurred while reading or writing data.");
	            e.printStackTrace();

	        } finally {
	            try {
	                if (fw != null) fw.close();
	                br.close();
	            } catch (IOException e) {
	                System.out.println("Error while closing resources.");
	            }
	        }
	    }
	}


