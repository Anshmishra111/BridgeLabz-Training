package linear_and_binary_search;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class ReadFileLineByLine {
	public static void main(String[] args) {
		String filePath="sample.txt";
		try {
			//step 1:create fileReader
			FileReader fileReader=new FileReader(filePath);
			//step 2: wrap with bufferedReader
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			
			String line;
			
			//Step 3: Read file line by line
			while((line=bufferedReader.readLine())!=null) {
				System.out.println(line);
			}
			//step 4: close resources
			bufferedReader.close();
			fileReader.close();
		}
		catch(IOException e){
			System.out.println("error raeding file: " + e.getMessage());
			
		}
	}

}
