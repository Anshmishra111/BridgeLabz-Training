package string;
import java.util.Scanner;
public class RemoveSpecificChar {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter a string : ");
	String text=sc.nextLine();
	System.out.print("Enter character to remove : ");
	char removeChar=sc.next().charAt(0);
	String result="";
	for(int i=0;i<text.length();i++) {
		char ch=text.charAt(i);
		
	
	if(ch!=removeChar) {
		result=result+ch;
	}
	
}
	System.out.println("Modified String : " + result);
}
}
