package string;
import java.util.Scanner;
public class ReverseString {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a String : ");
		String text=sc.nextLine();
		String reversed="";
		for(int i=text.length()-1;i>=0;i--) {
			reversed=reversed+text.charAt(i);
		}
		System.out.println("Reverse string : " + reversed);
		sc.close();
		
	}

}
