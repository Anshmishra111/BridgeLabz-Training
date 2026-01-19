package linear_and_binary_search;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class ReadFileUsingInputStreamReader {
	public static void main(String[] args) {
		String filePath="sample.txt";
		try {
			//step 1: create fileinputstram(byte stream)
			FileInputStream fis=new FileInputStream(filePath);
			//step 2: wrap with fileinputstream(byte-char)
			InputStreamReader isr=new InputStreamReader(fis,StandardCharsets.UTF_8);
			//step 3: wrap with bufferedreader with efficiency
			BufferedReader br=new BufferedReader(isr);
			String line;
			//read and print file line by line
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
			//step 5: close resources
			br.close();
			isr.close();
			fis.close();
			
		}catch(IOException e) {
			System.out.println("error reding file  : " + e.getMessage());
		}
	}

}
