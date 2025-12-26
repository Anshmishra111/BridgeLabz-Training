package string;
import java.util.Scanner;
public class LongestWord {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter a string : ");
		String text=sc.nextLine();
		String longestWord="";
		String currentWord="";
		for(int i=0;i<text.length();i++) {
			char ch=text.charAt(i);
			
			if(ch!=' ') {
				currentWord=currentWord+ch;
			}
			else {
				if(currentWord.length()>longestWord.length()) {
				longestWord=currentWord;
			}
			currentWord="";
		}
	}
		if(currentWord.length()>longestWord.length()) {
			longestWord=currentWord;
		}
		System.out.println("Longest Word : " + longestWord);

}
}
