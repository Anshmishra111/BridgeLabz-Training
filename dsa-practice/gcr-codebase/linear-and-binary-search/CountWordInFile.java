package linear_and_binary_search;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
public class CountWordInFile {
public static void main(String[] args) {
	String filePath="sample.txt";
	String targetWord="Java";
	int count=0;
	try {
		//step 1: create filereader and buffered reader
		FileReader fileReader=new FileReader(filePath);
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		String line;
		//step 2: read file line by line
		while((line=bufferedReader.readLine())!=null) {
			//Step 3: split line into words
			String[] words=line.split("\\s+");
			//step 4:check each word
			for(String word: words) {
				if(word.equalsIgnoreCase(targetWord)) {
					count++;
				}
			}
		}
		//step 5: close resources
		bufferedReader.close();
		fileReader.close();
		
		//step 6: print result
		System.out.println("Word \"" + targetWord + "\" occured " + count + " times.");
	}catch(IOException e) {
		System.out.println("Error raeding file : " + e.getMessage());
	}
}
}
