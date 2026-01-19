package linear_and_binary_search;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
public class ReadInputWriteToFile {
public static void main(String[] args) {
	String filePath="output.txt";
     try {
    	 //read input from console
    	 InputStreamReader isr=new InputStreamReader(System.in);
    	 BufferedReader br=new BufferedReader(isr);
    	 // step 2: create file writer
    	 FileWriter writer=new FileWriter(filePath,true);
    	 String input;
    	 System.out.println("Enter text(type 'exit' to stop: ");
    	 //step 3: read input until users types "exit"
    	 while(true) {
    		 input=br.readLine();
    		 if(input.equalsIgnoreCase("exit")) {
    		 		 break;
    	 }
    	 //step 4: write input to file
    	 writer.write(input + "\n");
     }
     //step 5 : close resources
     writer.close();
     br.close();
     isr.close();
     System.out.println("Input saved to file successfully");
     
}catch (IOException e){
	System.out.println("error : " + e.getMessage());

}
}
}