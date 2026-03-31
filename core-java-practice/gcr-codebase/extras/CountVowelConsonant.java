package string;
import java.util.Scanner;
public class CountVowelConsonant {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int vowels=0;
		int consonant=0;
		System.out.print("Enter a string : ");
		String text=sc.nextLine();
		text=text.toLowerCase();
		for(int i=0;i<=text.length();i++) {
			char ch=text.charAt(i);
		
		if(ch>='a'&&ch<='z') {
			if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u') {
				vowels++;
			}else {
				consonant++;
			}
		}
	}
	System.out.println("no. of vowels " + vowels);
	System.out.println("no. of consonant " + consonant);

}
}
