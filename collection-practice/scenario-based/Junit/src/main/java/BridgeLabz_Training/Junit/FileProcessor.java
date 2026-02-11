package BridgeLabz_Training.Junit;
import java.io.*;
public class FileProcessor {
//write content to file
	public void writeToFile(String fileName,String content) throws IOException{
		try(FileWriter writer=new FileWriter(fileName)){
			writer.write(content);
		}
	}
	//read content from file
	public String readFromFile(String fileName) throws IOException{
		StringBuilder sb=new StringBuilder();
		try 
			(BufferedReader br=new BufferedReader(new FileReader(fileName))){
			String line;
			while((line=br.readLine())!=null) {
				sb.append(line);
			}
		}
		return sb.toString();
		}
		
		
}
