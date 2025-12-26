package string;
import java.util.Scanner;
public class MostFrequentCharacter {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.print("Enter a string : ");
	String text=sc.nextLine();
	int maxCount=0;
	char mostFrequent=' ';
	for(int i=0;i<text.length();i++) {
		char ch=text.charAt(i);
		int count=1;
		
		for(int j=i+1;j<text.length();j++) {
			if(ch==text.charAt(j)) {
				count++;
			}
		}
		if(count>maxCount) {
			maxCount=count;
			mostFrequent=ch;
		}
	}
		System.out.println("most frequent charater : " + mostFrequent + "'");
}
}
