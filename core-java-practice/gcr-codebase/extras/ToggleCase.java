package string;
import java.util.Scanner;
public class ToggleCase {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a string : ");
		String text=sc.nextLine();
		String result="";
		for(int i=0;i<text.length();i++) {
			char ch=text.charAt(i);
			if(ch>='A'&&ch<'Z') {
				ch=(char)(ch+32);
			}
			else if(ch>='a'&&ch<='z') {
					ch=(char)(ch-32);
				}
				result=result+ch;
			}
			System.out.println("Toggle case : " + result);
		}
	}


