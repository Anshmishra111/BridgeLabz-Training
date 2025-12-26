package string;
import java.util.Scanner;
public class RemoveDuplicates {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("Enetr a String : ");
	String text=sc.nextLine();
	String result="";
	for(int i=0;i<text.length();i++) {
		char ch=text.charAt(i);
		
	
	boolean isDuplicate=false;
	
	for(int j=0;j<result.length();j++) {
		if(ch==result.charAt(j)) {
			isDuplicate=true;
			break;
		}
	}
	if(!isDuplicate) {
		result=result+ch;
	}
}
System.out.println("String after removing duplicates : " + result);
}
}