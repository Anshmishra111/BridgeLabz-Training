package string;
import java.util.Scanner;
public class CompareTwoSring {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter first string : ");
		String s1=sc.nextLine();
		System.out.print("Enter second string : ");
		String s2=sc.nextLine();
		int i=0;
		while(i<s1.length()&&i<s2.length()) {
			if(s1.charAt(i)!=s2.charAt(i)) {
				break;
			}
			i++;
		}
		if(i==s1.length() && i==s2.length()){
			System.out.println("Both strings are equal");
		}
		else if(i==s1.length()) {
			System.out.println("\"" + s1 + "\" comes before \"" + s2 + "\"");
		}
		else if(i==s2.length()) {
			System.out.println("\"" + s2 + "\" comes before \"" + s1 + "\"");
		}
		else if(s1.charAt(i)<s2.charAt(i)) {
				System.out.println("\"" + s1 + "\" comes before \"" + s2 + "\"");
		}
		else {
			System.out.println("\"" + s2 + "\" comes before \"" + s1 + "\"");
		}
		sc.close();
	}

}
