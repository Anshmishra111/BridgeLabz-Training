package string;
import java.util.Scanner;
public class SubSsringOccurences {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter main string : ");
		String text=sc.nextLine();
		System.out.print("Enter sub string : ");
		String sub=sc.nextLine();
		int count=0;
		int textLength=text.length();
		int subLength=sub.length();
		for(int i=0;i<textLength-subLength;i++) {
			boolean match =true;
			 for(int j=0;j<subLength;j++) {
				 if(text.charAt(i+j)!=sub.charAt(j)) {
					 match=false;
					 break;
				 }
			 }
			 if(match) {
				 count++;
			 }
		}
		System.out.println("Substring occurs " + count + "times");
		
	}

}
